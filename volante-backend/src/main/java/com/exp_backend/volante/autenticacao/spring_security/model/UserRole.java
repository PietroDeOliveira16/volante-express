package com.exp_backend.volante.autenticacao.spring_security.model;

public enum UserRole {
    ADMIN("admin"),
    VENDEDOR("vendedor"),
    SECRETARIO("secretario"),
    CLIENTE("cliente");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
