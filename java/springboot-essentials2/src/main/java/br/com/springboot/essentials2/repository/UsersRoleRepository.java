package br.com.springboot.essentials2.repository;

import br.com.springboot.essentials2.model.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

//Repository criado para fazer as consultas de UsersRole pelo security
public interface UsersRoleRepository extends JpaRepository<UsersRole, Long> {

//    Importante colocar como UserDetails pq vai ser usado pelo security depois
    UserDetails findByLogin(String login);

}
