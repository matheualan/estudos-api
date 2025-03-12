package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.repository.UsersRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Por meio do 'implements UserDetailsService' o spring security vai entender que deve chamar automaticamente toda vez q um usuario
//precisar se autenticar
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsersRoleRepository usersRoleRepository;

//Metodo onde o Security vai consultar os users no DataBase na tabela criada p/ as auth toda vez q algum usuario tentar
//se autenticar na aplicacao
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRoleRepository.findByLogin(username);
    }

}
