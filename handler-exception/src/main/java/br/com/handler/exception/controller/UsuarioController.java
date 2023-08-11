package br.com.handler.exception.controller;

import br.com.handler.exception.dto.UsuarioDTO;
import br.com.handler.exception.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioDTO));
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorCpf(@RequestParam String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarioPorCpf(cpf));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

}
