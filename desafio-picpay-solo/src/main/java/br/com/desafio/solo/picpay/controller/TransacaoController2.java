package br.com.desafio.solo.picpay.controller;

import br.com.desafio.solo.picpay.domain.transacao.Transacao;
import br.com.desafio.solo.picpay.dto.record.TransacaoDTO;
import br.com.desafio.solo.picpay.service.TransacaoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaction2")
public class TransacaoController2 {

    @Autowired
    private TransacaoService2 transacaoService2;

    @PostMapping
    public ResponseEntity<Transacao> realizarTransacao(@RequestBody TransacaoDTO transacaoDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService2.createTranscation(transacaoDTO));
    }

}