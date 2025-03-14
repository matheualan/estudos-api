package br.com.springboot.essentials2.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Classe para desabilitar as configuracoes padroes do S.Security e aplicar as nossas proprias configuracoes
@Configuration //Avisa ao Spring que eh uma classe de configuracao
@EnableWebSecurity //Avisa ao S.Security que iremos desabilitar as configs default e pede para habilitar as configuracoes
// do web security que vamos fazer nossa propria config
public class SecurityConfig {

    @Bean //Para o Spring fazer a injecao e gerenciar as instancias desse metodo/objeto
//SecurityFilterChain: Sao filtros que serao aplicado a requisicao para fazer a seguranca da app.
//Filtros: Metodos onde vamos fazer validacoes no usuario q esta fazendo a requisicao para verificar se esta apto ou nao
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Definindo auth stateless
                .authorizeHttpRequests(authorize -> authorize //Definindo quais URLs/Requisicoes HTTP precisarao ser autenticadas
                        .requestMatchers(HttpMethod.POST, "/client/save").hasRole("ADMIN") //Config para somente ADMIN poder realizar request http para o endpoint selecionado
                        .anyRequest().authenticated()) //Config para qualquer Role, apenas autenticado
                .build();
    }

//Metodo para avisar ao S.Security de onde esta vindo a instancia de AuthenticationManager q esta sendo usado em AuthenticationController
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); //Instancia de AuthenticationManager
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //Classe do S.Security p/ fazer criptografia pode meio de algoritmo de hash
    }

}
