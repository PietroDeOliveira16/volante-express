package com.exp_backend.volante.autenticacao.spring_security.model;

import jakarta.persistence.*;

/**
 * Modelo que representa usuários do sistema
 */
@Entity
@Table(name = "usuario")
public class M_Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String username; // E-mail

    private String password;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_imagem")
    private M_Imagem imagem;

    private UserRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public M_Imagem getImagem() {
        return imagem;
    }

    public void setImagem(M_Imagem imagem) {
        this.imagem = imagem;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
