package br.com.desafio.backend.picpay.desafiobackendpicpay.domain.repository;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByDocument(String document);

}
