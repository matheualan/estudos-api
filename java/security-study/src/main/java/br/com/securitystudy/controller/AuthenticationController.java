package br.com.securitystudy.controller;

import br.com.securitystudy.dto.AuthenticationDTO;
import br.com.securitystudy.dto.RegisterDTO;
import br.com.securitystudy.model.Users;
import br.com.securitystudy.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {


    @Autowired
    UsersRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticate = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (repository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Users newUser = new Users(registerDTO.login(), encryptedPassword, registerDTO.role());

        Users save = repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registerDTO);
    }

}