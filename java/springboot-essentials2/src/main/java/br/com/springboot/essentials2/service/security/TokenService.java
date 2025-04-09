package br.com.springboot.essentials2.service.security;

import br.com.springboot.essentials2.model.security.UsersRole;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService { //classe para criacao e validacao do token

    @Value("${api.security.token.secret}") //o valor esta vindo do application.properties
    private String secret; //a secret pode ser variaveis de ambiente q podem estar na aws ou na maquina local
    //a secret eh uma forma para tornar o hash gerado (token) unico na app
    //a secret tem q ser mto bem guardada pois eh uma informacao unica e sensivel q ngm deve ter acesso pois poderia descobrir as senhas

    public String generateToken(UsersRole usersRole) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret); //algoritmo de geracao de token HMAC256
            return JWT.create()
                    .withIssuer("springboot-essentials2") //nome do emissor, de quem esta criando, bom colocar o nome da api
                    .withSubject(usersRole.getLogin()) //para o usuario que esta sendo criado esse token, usar o login do usuario
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))) //o tempo de expiracao desse token
                    .sign(algorithm); //para fazer a assinatura e a geracao final, usar a variavel q criamos com a secret
        } catch (JWTCreationException exc) {
            throw new RuntimeException("Error while generating token", exc);
        }
    }

    //Metodo para validar o token durante as requisicoes q o usuario estiver fazendo
    //esse metodo vai decryptar o token para verificar o login do usuario
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("springboot-essentials2")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exc) {
            return ""; //retornando string vazia pq qdo algum metodo chamar esse metodo e receber string vazia ele
                       //vai saber q o token nao foi validado e retornar unauthorized
        }
    }

}
