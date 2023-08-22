package br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.TypeUser;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, String email,
                      String password, BigDecimal balance, TypeUser userType) {
}
