package br.com.springboot.essentials2.service.security;

import br.com.springboot.essentials2.repository.security.UsersRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Esse servico vai ser chamado automaticamente pelo S.Security toda vez que um usuario precisar se autenticar
//Por meio do 'implements UserDetailsService' o S.Security vai identificar que deve chamar automaticamente toda
//vez q um usuario precisar se autenticar

//Dentro dessa classe vamos definir alguns metodos que vao ajudar o S.Security identificar como queremos fazer essas autenticacoes
@Service
public class AuthorizationService implements UserDetailsService {
//UserDetailsService: Para o S.Security identificar que essa eh nossa authentication service que ele deve chamar de forma automatica

    @Autowired
    UsersRoleRepository usersRoleRepository;

//Toda vez que alguem tentar se autenticar na app o S.Security tem que ter uma forma de consultar esses users
//Metodo onde o Security vai consultar os users no database na tabela criada p/ as auth toda vez q algum usuario tentar
//se autenticar na aplicacao
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRoleRepository.findByLogin(username);
    }

}
