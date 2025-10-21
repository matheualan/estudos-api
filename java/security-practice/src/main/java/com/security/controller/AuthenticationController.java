package com.security.controller;

import com.security.dto.LoginDTO;
import com.security.dto.RegisterDTO;
import com.security.dto.TokenDTO;
import com.security.model.Users;
import com.security.repository.UsersRepository;
import com.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final TokenService tokenService;

    @PostMapping(path = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken((Users) authenticate.getPrincipal());
        return ResponseEntity.ok().body(new TokenDTO(token));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO data) {
        if (usersRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String passwordEncode = new BCryptPasswordEncoder().encode(data.password());
        Users users = new Users(data.login(), passwordEncode, data.role());
        usersRepository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
