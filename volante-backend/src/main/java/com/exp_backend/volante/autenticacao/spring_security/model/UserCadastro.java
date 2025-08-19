package com.exp_backend.volante.autenticacao.spring_security.model;

/**
 * Classe que representa uma mensagem post de cadastro para {@link com.exp_backend.volante.autenticacao.spring_security.controller.C_Usuario#cadastrar(UserCadastro)}
 */

public class UserCadastro {

    private final String nome;
    private final String cpf;
    private final String telefone;
    private final M_Arquivo foto_de_perfil; // TODO: Implementar IMAGENS
    private final String email;
    private final String senha;

    public UserCadastro(String nome, String cpf, String telefone, M_Arquivo foto_de_perfil, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.foto_de_perfil = foto_de_perfil;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public M_Arquivo getFoto_de_perfil() {
        return foto_de_perfil;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
