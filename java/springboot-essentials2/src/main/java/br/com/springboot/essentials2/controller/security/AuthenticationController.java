package br.com.springboot.essentials2.controller.security;

import br.com.springboot.essentials2.dto.security.AuthenticationDTO;
import br.com.springboot.essentials2.dto.security.LoginResponseDTO;
import br.com.springboot.essentials2.dto.security.RegisterDTO;
import br.com.springboot.essentials2.model.security.UsersRole;
import br.com.springboot.essentials2.repository.security.UsersRoleRepository;
import br.com.springboot.essentials2.service.security.TokenService;
import br.com.springboot.essentials2.util.DateUtil;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/auth")
@Log4j2
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRoleRepository usersRoleRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    DateUtil dateUtil;

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST /auth/login"));

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password()); //Acho q eh para fazer um token do login e password
        var auth = this.authenticationManager.authenticate(usernamePassword); //Para autenticar o token

        var token = tokenService.generateToken((UsersRole) auth.getPrincipal()); //qdo o usuario logar vai receber um token

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST /auth/register"));

        if (usersRoleRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsersRole newUser = new UsersRole(data.login(), encryptedPassword, data.role());

        usersRoleRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
