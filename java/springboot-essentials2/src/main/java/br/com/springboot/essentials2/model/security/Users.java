package br.com.springboot.essentials2.model.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_users_role") //Seria melhor tb_user_model_for_auth
@Getter
@NoArgsConstructor
@AllArgsConstructor
//Users: Classe para armazenar os dados do usuario para autenticacao [nome melhor: UserModelForAuth]
//UserDetails: Implementacao para o S.Security identificar a classe que sera usada para autenticar usuarios na app
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    private String password;

    private UsersRole role;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime createdAt = LocalDateTime.now();

    public Users(String login, String password, UsersRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

//Metodo para o S.Security consultar a entidade e verificar as roles que ela possui
//Aqui deve retornar as roles do user p/ o S.Security tomar a decisao correta de acordo com a permissao da role do user. Ex.: permitir ou nao acesso a um endpoint
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsersRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                           new SimpleGrantedAuthority("ROLE_MANAGER"),
                           new SimpleGrantedAuthority("ROLE_USER"));
        } else if (this.role == UsersRole.MANAGER) {
            return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"),
                           new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.login;
    }

//Alterar todos retornos para true pois n ira ser utilizado para auth simples
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
