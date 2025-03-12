package br.com.springboot.essentials2.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//Classe para desabilitar as configuracoes padroes do S.Security e aplicar as nossas proprias configuracoes
@Configuration //Avisa ao Spring que eh uma classe de configuracao
@EnableWebSecurity //Avisa ao S.Security que iremos desabilitar as configs default e pede para habilitar as configuracoes
// do web security que vamos fazer nossa propria config
public class SecurityConfig {

    @Bean //Para o Spring gerenciar as instancias desse metodo/objeto
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> authorize.requestMatchers())
                .build();
    }

}
