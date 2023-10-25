package br.com.controlevendas.controller.users;

import br.com.controlevendas.model.User;
import br.com.controlevendas.service.users.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users-entity")
public class UserEntityController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.listAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.createUser(user));
    }

}
