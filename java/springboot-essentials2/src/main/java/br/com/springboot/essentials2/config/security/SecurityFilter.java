package br.com.springboot.essentials2.config.security;

import br.com.springboot.essentials2.model.security.UsersRole;
import br.com.springboot.essentials2.repository.security.UsersRoleRepository;
import br.com.springboot.essentials2.service.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter { //Once eh um filtro q acontece uma vez a cada requisicao para pegar o token e validar

    @Autowired
    TokenService tokenService;

    @Autowired
    UsersRoleRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(login);
        }
        filterChain.doFilter(request, response); //para chamar o prox filtro la no addFilterBefore q esta na classe SecurityConfig caso ja tenha terminado td aqui
        //deve cair neste metodo caso nao tenha token, caso nao tenha passado na validicao do if acima
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null; //quer dizer q nao tem nenhum token nessa requisicao
        return authHeader.replace("Bearer ", "");
    }

}
