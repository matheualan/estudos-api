package br.com.desafio.backend.picpay.desafiobackendpicpay.controller;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.transaction.Transaction;
import br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record.TransactionDTO;
import br.com.desafio.backend.picpay.desafiobackendpicpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionDTO));
    }

}
