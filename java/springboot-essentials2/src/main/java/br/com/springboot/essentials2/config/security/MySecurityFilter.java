package br.com.springboot.essentials2.config.security;

import br.com.springboot.essentials2.repository.security.UsersRepository;
import br.com.springboot.essentials2.service.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//classe para o spring verificar o token do usuario de forma automatica em todas as requisicoes q necessitam de autorizacao
public class MySecurityFilter extends OncePerRequestFilter { //Once eh um filtro q acontece uma vez a cada requisicao para pegar o token e validar verificando as infos contidas nesse token

    @Autowired
    TokenService tokenService;

    @Autowired
    UsersRepository userRepository;

    @Override //doFilterInternal eh o filtro q vai ser chamado antes do UsernamePasswordAuthenticationFilter na classe SecurityConfig
    //eh aqui q vamos pegar o token e recuperar as infos contidas no token
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Passando pelo filtro doFilterInternal() na classe MySecurityConfig");

        var token = this.recoverToken(request);
        if (token != null) {
            var subjectLogin = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(subjectLogin);

            //verificacoes do usuario
            //dessa forma pegamos todas as infos q o S.Security ira precisar para fazer as validacoes nas requisicoes dos endpoints q precisam de autenticacao
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            //salvar no contexto da requisicao
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response); //para chamar o prox filtro q no caso eh UsernamePasswordAuthenticationFilter la no addFilterBefore
        //q esta na classe SecurityConfig. Deve cair nesse metodo caso nao tenha token, caso nao tenha passado na validicao do if acima
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization"); //header authorization vai ser onde o usuario vai passar o token
        if (authHeader == null) return null; //quer dizer q nao tem nenhum token nessa requisicao
        return authHeader.replace("Bearer ", ""); //substituindo "Bearer " por um espaco vazio para pegar apenas o valor do token no final e descartar o bearer q serve apenas por padronizacao
    }
}
