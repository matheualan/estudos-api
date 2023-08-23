package br.com.desafio.solo.picpay.dto.record;

import br.com.desafio.solo.picpay.domain.user.TipoUsuario;

import java.math.BigDecimal;

public record UsuarioDTO(String nome, String sobrenome, String cpf,
                         String email, String senha, TipoUsuario tipoUsuario,
                         BigDecimal saldo) {
}