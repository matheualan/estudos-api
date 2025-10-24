package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class ControllerTest {

    @GetMapping(path = "/public")
    public String publicMethod() {
        System.out.println("LOG Public Method");
        return "LOG Public Method";
    }

    @PostMapping(path = "/private")
    public String privateMethod() {
        System.out.println("LOG Private Method");
        return "LOG Private Method";
    }

}
