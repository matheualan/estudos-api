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
@Table(name = "tb_users_role") // seria melhor tb_user_model_for_auth
@Getter
@NoArgsConstructor
@AllArgsConstructor
//UsersRole classe criada para armazenar os dados do usuario para autenticacao name: UserModelForAuth
public class UsersRole implements UserDetails { //UserDetails para o security identificar uma classe que sera usada para autenticacao

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private UserRoleEnum role;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime createdAt = LocalDateTime.now();

//Esse metodo consulta a entidade para verificar as roles que ela possui
//Aqui deve retornar as roles do user p/ o security tomar as decicoes/permissoes corretas de acordo com a role do user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRoleEnum.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
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
