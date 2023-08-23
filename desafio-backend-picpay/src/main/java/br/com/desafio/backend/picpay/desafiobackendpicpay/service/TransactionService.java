package br.com.desafio.backend.picpay.desafiobackendpicpay.service;

import br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record.TransactionDTO;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.transaction.Transaction;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import br.com.desafio.backend.picpay.desafiobackendpicpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RestTemplate restTemplate;

    private final WebClient webClient;
//  "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6"
    public TransactionService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.build();
    }

//    método para criar transações
    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
//        Pega o usuário que enviará para fazer a validação
        User sender = userService.findUserById(transactionDTO.senderId());
//        Pega o usuário que receberá para atualizar o balance
        User receiver = userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());

//        Autoriza ou não a transação
//        boolean isAuthorizedRestTemplate = authorizeTransactionRestTemplate(sender, transactionDTO.value());
        boolean isAuthorizedWebClient = authorizeTransactionRestTemplate(sender, transactionDTO.value());
        if (!isAuthorizedWebClient) {
            throw new Exception("Transação não autorizada!!!");
        }

//        Cria nova transação e pega os dados da transação
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
//        BeanUtils.copyProperties(transactionDTO, newTransaction);

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        notificationService.sendNotifyRestTemplate(sender, "Transação realizada com sucesso");
        notificationService.sendNotifyRestTemplate(receiver, "Transação recebida com sucesso");

        return newTransaction;
    }

    public boolean authorizeTransactionWebClient(User sender, BigDecimal value) {
        Mono<Map> authorizationResponse = webClient.get()
                .uri("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6")
                .retrieve()
                .bodyToMono(Map.class);

        if (authorizationResponse.toString().equalsIgnoreCase("Autorizado")) {
            return true;
        } else return false;
    }

    public boolean authorizeTransactionRestTemplate(User sender, BigDecimal amount) {
        ResponseEntity<Map> respostaAutorizacao =
                restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (respostaAutorizacao.getStatusCode() == HttpStatus.OK) {
            String message = (String) respostaAutorizacao.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }

}