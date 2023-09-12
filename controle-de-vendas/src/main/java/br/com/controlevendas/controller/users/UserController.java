package br.com.controlevendas.controller.users;

import br.com.controlevendas.dto.UserDTO;
import br.com.controlevendas.service.users.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
    }

    @PostMapping(path = "/save-several")
    public ResponseEntity<List<UserDTO>> saveListUsers(@RequestBody @Valid List<UserDTO> usersDTO) {
        return new ResponseEntity<List<UserDTO>>(userService.saveSeveralUsers(usersDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers());
    }

    @GetMapping(path = "/user/{cpf}")
    public ResponseEntity<UserDTO> findUserByCpf(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByCpf(cpf));
    }

    @GetMapping(path = "/users/{firstName}")
    public ResponseEntity<List<UserDTO>> findAllUsersByFirstName(@PathVariable String firstName) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsersByFirstName(firstName));
    }

    @DeleteMapping(path = "/deleteById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "/deleteByCpf/{cpf}")
    public ResponseEntity<Void> deleteUserByCpf(@PathVariable String cpf) {
        userService.deleteUserByCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/update/{cpf}")
    public ResponseEntity<UserDTO> updateUserByCpf(@PathVariable String cpf, @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.userUpdate(cpf, userDTO));
    }

}
