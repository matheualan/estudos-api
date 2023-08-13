package br.com.handler.exception.controller;

import br.com.handler.exception.dto.CepEnderecoDTO;
import br.com.handler.exception.dto.UsuarioDTO;
import br.com.handler.exception.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioDTO));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorCpf(@RequestParam String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarioPorCpf(cpf));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @GetMapping(path = "/cep/")
    public ResponseEntity<Mono<CepEnderecoDTO>> buscarEnderecoPorCep(@RequestParam String cep) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getEnderecoByCep(cep));
    }

}
