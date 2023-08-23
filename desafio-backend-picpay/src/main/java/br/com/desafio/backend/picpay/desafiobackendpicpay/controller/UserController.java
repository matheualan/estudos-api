package br.com.desafio.backend.picpay.desafiobackendpicpay.controller;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record.UserDTO;
import br.com.desafio.backend.picpay.desafiobackendpicpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listUsers());
    }

}
