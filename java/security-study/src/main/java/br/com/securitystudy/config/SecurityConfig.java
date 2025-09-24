package br.com.securitystudy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MySecurityFilter mySecurityFilter;
    private final Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/test/save").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/test/only-admin").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/test/").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll();

                    // se estiver rodando no profile "dev", libera Swagger + H2
                    if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
                        auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
                        auth.requestMatchers("/h2-console/**").permitAll();
                    }

                    auth.anyRequest().authenticated();
                }).addFilterBefore(mySecurityFilter, UsernamePasswordAuthenticationFilter.class);

        // H2 usa frames, precisa desabilitar isso só em dev
        if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        }

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
