package br.com.securitystudy.controller;

import br.com.securitystudy.dto.AuthenticationDTO;
import br.com.securitystudy.dto.LoginResponseDTO;
import br.com.securitystudy.dto.RegisterDTO;
import br.com.securitystudy.model.Users;
import br.com.securitystudy.repository.UsersRepository;
import br.com.securitystudy.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthenticationController {

    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO>  login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Users> register(@RequestBody @Valid RegisterDTO data) {
        if (usersRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(data.login(), encryptedPassword, data.role());

        Users save = usersRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

}