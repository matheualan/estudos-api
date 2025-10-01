package com.security.dto;

import com.security.model.UsersRole;

public record RegisterDTO(String login, String password, UsersRole role) {
}
