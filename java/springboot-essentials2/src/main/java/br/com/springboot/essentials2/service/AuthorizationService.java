package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.repository.UsersRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsersRoleRepository usersRoleRepository;

//    Esse eh o metodo onde o spring security vai consultar os usuarios no banco de dados na tabela q criou p as auth
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRoleRepository.findByLogin(username);
    }

}
