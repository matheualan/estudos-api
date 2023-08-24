package br.com.desafio.solo.picpay.repository;

import br.com.desafio.solo.picpay.domain.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
