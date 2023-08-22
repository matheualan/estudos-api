package br.com.desafio.backend.picpay.desafiobackendpicpay.service;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.dto.TransactionDTO;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.transaction.Transaction;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import br.com.desafio.backend.picpay.desafiobackendpicpay.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
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
    private RestTemplate restTemplate;

    private final WebClient webClient;
//  "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6"
    public TransactionService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.build();
    }

//    método para criar transações
    public void createTransaction(TransactionDTO transactionDTO) throws Exception {
//        Pega o usuário que enviará para fazer a validação
        User sender = userService.findUserById(transactionDTO.senderId());
//        Pega o usuário que receberá para atualizar o balance
        User receiver = userService.findUserById(transactionDTO.senderId());

        userService.validateTransaction(sender, transactionDTO.value());

//        Autoriza ou não a transação
        boolean isAuthorizedRestTemplate = authorizeTransactionRestTemplate(sender, transactionDTO.value());
        boolean isAuthorizedWebClient = authorizeTransactionWebClient(sender, transactionDTO.value());
        if (!isAuthorizedRestTemplate) {
            throw new Exception("Transação não autorizada");
        }

//        Cria nova transação e pega os dados da transação
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
//        BeanUtils.copyProperties(transactionDTO, transaction);

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        transactionRepository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

    }

    public boolean authorizeTransactionWebClient(User sender, BigDecimal value) {
        Mono<String> message = webClient.get()
                .uri("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6")
                .retrieve()
                .bodyToMono(String.class);

        if (message.toString().equalsIgnoreCase("Autorizado")) {
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