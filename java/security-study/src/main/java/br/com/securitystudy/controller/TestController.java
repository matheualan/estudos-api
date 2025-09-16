package br.com.securitystudy.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @GetMapping(path = "/")
    public String test() {
        return "Hello world! Tudo funcionando por aqui :)";
    }

    @GetMapping(path = "/only-admin")
    public String testApenasAdmin() {
        return "Olá Sr. Administrador, fique a vontade.";
    }

    @PostMapping(path = "/save") //save?name="matheus"
    public String testSave(@RequestParam String name) {
        return "Nome salvo: " + name;
    }

}