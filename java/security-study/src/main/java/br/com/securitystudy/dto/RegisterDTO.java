package br.com.securitystudy.dto;

import br.com.securitystudy.model.UsersRole;

public record RegisterDTO(String login, String password, UsersRole role) {
}
