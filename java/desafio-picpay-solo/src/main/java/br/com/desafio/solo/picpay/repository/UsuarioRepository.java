package br.com.desafio.solo.picpay.repository;

import br.com.desafio.solo.picpay.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByCpf(String cpf);

}
