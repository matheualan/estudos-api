package com.salescontrol.repository;

import com.salescontrol.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByName(String name);
    Optional<Client> findByNickname(String nickname);
    Optional<Client> findByCpf(String cpf);

}
