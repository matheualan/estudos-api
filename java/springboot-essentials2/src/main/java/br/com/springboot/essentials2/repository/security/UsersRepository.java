package br.com.springboot.essentials2.repository.security;

import br.com.springboot.essentials2.model.security.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    //S.Security usará para fazer as consultas dos users no DataBase buscando por login
//Importante retornar como UserDetails pq sera usado pelo S.Security
    UserDetails findByLogin(String login);

}
