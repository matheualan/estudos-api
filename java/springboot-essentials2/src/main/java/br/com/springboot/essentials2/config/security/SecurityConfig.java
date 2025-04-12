package br.com.springboot.essentials2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Classe para desabilitar as configuracoes padroes do S.Security e aplicar as nossas proprias configuracoes
@Configuration //Avisa ao Spring que eh uma classe de configuracao
@EnableWebSecurity
//Avisa ao S.Security que iremos desabilitar as configs default e pede para habilitar as configuracoes
//do web security pois vamos fazer nossa propria config
public class SecurityConfig {

    @Autowired
    MySecurityFilter mySecurityFilter;

//    @Bean //Para o Spring fazer a injecao e gerenciar as instancias desse metodo/objeto
//SecurityFilterChain: Sao filtros que serao aplicados a requisicao para fazer a seguranca da app.
//Filtros: Metodos onde vamos fazer validacoes no usuario q esta fazendo a requisicao para verificar se esta apto ou nao
//    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Definindo auth stateless
//                .authorizeHttpRequests(authorize -> authorize //Definindo quais URLs/Requisicoes HTTP precisarao ser autenticadas
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/client/save").hasRole("ADMIN") //Config para somente ADMIN poder realizar request http para o endpoint selecionado
//                        .anyRequest().authenticated()) //definindo que as demais urls da api precisarao de autenticacao
//
//                //securityFilter eh a classe q criamos para verificar os tokens de forma automatica a cada requisicao
//                //dps q ele passar pelos metodos acima e identificar o endpoint q precisa da validacao do token
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //quero que o filtro securityFilter ocorra antes do UsernamePassword...
//                //caso o securityFilter nao passe ira cair no UsernamePasswordAuthenticationFilter e provavelmente lancara um 403 forbbiden
//
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/client/list-all").permitAll()
                        .requestMatchers(HttpMethod.POST, "/client/save").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).addFilterBefore(mySecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //Metodo para avisar ao S.Security de onde esta vindo a instancia de AuthenticationManager q esta sendo usado em AuthenticationController
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); //Instancia de AuthenticationManager
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //Classe do S.Security p/ fazer criptografia por meio de algoritmo de hash
    }

    //Para q o Spring faça verificacao do token automaticamente em todas requisicoes q precisam de autorizacao

}
