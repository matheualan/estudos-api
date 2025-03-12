package br.com.springboot.essentials2.repository;

import br.com.springboot.essentials2.model.security.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRoleRepository extends JpaRepository<UsersRole, Long> {

//Importante retornar como UserDetails pq vai ser usado pelo security
    UserDetails findByLogin(String login);

}
