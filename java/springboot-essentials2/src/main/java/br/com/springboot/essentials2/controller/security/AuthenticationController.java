package br.com.springboot.essentials2.controller.security;

import br.com.springboot.essentials2.dto.security.AuthenticationDTO;
import br.com.springboot.essentials2.dto.security.RegisterDTO;
import br.com.springboot.essentials2.model.security.UsersRole;
import br.com.springboot.essentials2.repository.security.UsersRoleRepository;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRoleRepository usersRoleRepository;

    @Autowired
    private DateUtil dateUtil;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST /login"));

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password()); //Acho q eh para fazer um token do login e password
        var auth = this.authenticationManager.authenticate(usernamePassword); //Para autenticar o token

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST /register"));

        if (usersRoleRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsersRole newUser = new UsersRole(data.login(), encryptedPassword, data.role());

        usersRoleRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
