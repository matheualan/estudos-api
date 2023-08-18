package br.com.desafio.backend.picpay.desafiobackendpicpay.service;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.dto.TransactionDTO;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import br.com.desafio.backend.picpay.desafiobackendpicpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    private final WebClient webClient;

    public TransactionService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.build();
    }

    public void createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = userService.findUserById(transactionDTO.senderId());
        User receiver = userService.findUserById(transactionDTO.senderId());

        userService.validateTransaction(sender, transactionDTO.value());
    }

}
