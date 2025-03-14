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
//UsersRole: Classe criada para armazenar os dados do usuario para autenticacao [nome melhor: UserModelForAuth]
//UserDetails: Implementacao para o S.Security identificar a classe onde tera os dados usados para autenticacao
public class UsersRole implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private UserRoleEnum role;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime createdAt = LocalDateTime.now();

//Metodo para quando o S.Security for consultar a entidade e verificar as roles que ela possui
//Aqui deve retornar as roles do user p/ o S.Security tomar a decisao correta de acordo com a permissao da role do user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRoleEnum.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                           new SimpleGrantedAuthority("ROLE_MANAGER"),
                           new SimpleGrantedAuthority("ROLE_USER"));
//        } else if (this.role == UserRoleEnum.MANAGER) {
//            return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"),
//                           new SimpleGrantedAuthority("ROLE_USER"));
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
