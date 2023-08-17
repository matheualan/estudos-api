package br.com.desafio.backend.picpay.desafiobackendpicpay.domain.repository;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {



}
