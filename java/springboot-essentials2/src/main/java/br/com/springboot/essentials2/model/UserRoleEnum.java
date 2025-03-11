package br.com.springboot.essentials2.model;

public enum UserRoleEnum {

    ADMIN("AdmiN"), USER("UseR");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

}
