package br.com.controlevendas.controller;

import br.com.controlevendas.model.User;
import br.com.controlevendas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PostMapping(path = "/save-several")
    public ResponseEntity<List<User>> saveListUsers(@RequestBody List<User> users) {
        return new ResponseEntity<List<User>>(userRepository.saveAll(users), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

}
