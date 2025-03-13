package br.com.springboot.essentials2.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
//SecurityFilterChain: Sao filtros que serao aplicado a requisicao para fazer a seguranca da app.
//Filtros: Metodos onde vamos fazer validacoes no usuario q esta fazendo a requisicao para verificar se esta apto ou nao
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Definindo auth stateless
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/client/save").hasRole("ADMIN")
                        .anyRequest().authenticated())//Definindo quais URLs/Requisicoes HTTP precisarao ser autenticadas
                .build();
    }

}
