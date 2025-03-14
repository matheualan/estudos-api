package br.com.springboot.essentials2.model.security;

//Classe usada para defirnir as roles(permissoes/funcoes/papeis) que os usuarios terao na implementacao do S.Security
public enum UserRoleEnum {

    ADMIN("admin"), MANAGER("manager"), USER("user");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

}
