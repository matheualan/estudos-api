package br.com.springboot.essentials2.dto.security;

import br.com.springboot.essentials2.model.security.UsersRoleEnum;

public record RegisterDTO(String login, String password, UsersRoleEnum role) {
}
