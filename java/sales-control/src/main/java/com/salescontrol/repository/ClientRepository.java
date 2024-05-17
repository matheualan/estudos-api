package com.salescontrol.repository;

import com.salescontrol.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByName(String name);
    Client findByCpf(String cpf);

}
