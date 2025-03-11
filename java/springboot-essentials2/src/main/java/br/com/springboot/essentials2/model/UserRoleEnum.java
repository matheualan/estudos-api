package br.com.springboot.essentials2.model;

public enum UserRoleEnum {

    ADMIN("admin"), USER("user");

    private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

}
