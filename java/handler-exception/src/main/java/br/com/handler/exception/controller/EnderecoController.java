package br.com.handler.exception.controller;

import br.com.handler.exception.dto.EnderecoDTO;
import br.com.handler.exception.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.listarEnderecos());
    }

}
