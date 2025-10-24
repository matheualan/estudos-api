package com.security.config;

import com.security.repository.UsersRepository;
import com.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
//@Order(2)
public class MySecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("LOG do MySecurityFilter doFilterInternal()");
        String token = recoverToken(request);
        if (token != null) {
            String loginSubject = tokenService.validateToken(token);
            UserDetails userByLogin = usersRepository.findByLogin(loginSubject);
            if (userByLogin != null) {
                var authenticationToken = new UsernamePasswordAuthenticationToken(userByLogin, null, userByLogin.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }

}
