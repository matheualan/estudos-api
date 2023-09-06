package br.com.controlevendas.controller;

import br.com.controlevendas.dto.UserDTO;
import br.com.controlevendas.model.User;
import br.com.controlevendas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
    }

    @PostMapping(path = "/save-several")
    public ResponseEntity<List<UserDTO>> saveListUsers(@RequestBody List<UserDTO> usersDTO) {
        return new ResponseEntity<List<UserDTO>>(userService.saveSeveralUsers(usersDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers());
    }

    @GetMapping(path = "/{firstName}")
    public ResponseEntity<UserDTO> findUserByFirstName(@PathVariable String firstName) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByFirstName(firstName));
    }

    @DeleteMapping(path = "/{firstName}")
    public ResponseEntity<Void> deleteUserByFirstName(@PathVariable String firstName) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
