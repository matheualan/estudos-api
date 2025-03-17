package br.com.springboot.essentials2.controller.security;

import br.com.springboot.essentials2.dto.security.AuthenticationDTO;
import br.com.springboot.essentials2.dto.security.RegisterDTO;
import br.com.springboot.essentials2.model.security.UsersRole;
import br.com.springboot.essentials2.repository.security.UsersRoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRoleRepository usersRoleRepository;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password()); //Acho q eh para fazer um token do login e password
        var auth = this.authenticationManager.authenticate(usernamePassword); //Para autenticar o token
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (usersRoleRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsersRole newUser = new UsersRole(data.login(), encryptedPassword, data.role());

        usersRoleRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
