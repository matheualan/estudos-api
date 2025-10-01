package com.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @GetMapping(path = "/forUser")
    public ResponseEntity<String> returnForUser() {
        return ResponseEntity.status(HttpStatus.OK).body("Olá pequeno usuário.");
    }

    @GetMapping(path = "/forAdmin")
    public ResponseEntity<String> returnForAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body("Sinta-se avontade Sr. ADMIN! Faça o que quiser!");
    }

}