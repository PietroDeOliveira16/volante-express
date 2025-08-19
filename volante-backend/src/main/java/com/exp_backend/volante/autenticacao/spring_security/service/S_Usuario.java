package com.exp_backend.volante.autenticacao.spring_security.service;

import com.exp_backend.volante.autenticacao.spring_security.model.M_Usuario;
import com.exp_backend.volante.autenticacao.spring_security.model.UserCadastro;
import com.exp_backend.volante.autenticacao.spring_security.model.UserRole;
import com.exp_backend.volante.autenticacao.spring_security.repository.R_Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service para a tabela {@link com.exp_backend.volante.autenticacao.spring_security.model.M_Usuario}
 */
@Service
public class S_Usuario {
    private final R_Usuario r_usuario;
    private final BCryptPasswordEncoder encoder;
    private final Pattern senha_valida;

    public S_Usuario(R_Usuario r_usuario) {
        this.r_usuario = r_usuario;
        this.encoder = new BCryptPasswordEncoder(12);
        this.senha_valida = Pattern.compile("(?=.*[a-z]+)" +
                                            "(?=.*[A-Z]+)" +
                                            "(?=.*[0-9]+)" +
                                            "(?=.*[!-\\/:-@\\[-`{-~]+)");
    }

    /**
     * Cria um usuário e o cadastra a partir de um {@link UserCadastro}
     */
    public M_Usuario cadastrarUsuario(UserCadastro cadastro) {
        M_Usuario usuario = new M_Usuario();

        usuario.setNome(cadastro.getNome());
        usuario.setCpf(new BigDecimal(cadastro.getCpf()));

        usuario.setTelefone(cadastro.getTelefone());
        usuario.setUsername(cadastro.getEmail());
        usuario.setPassword(encoder.encode(cadastro.getSenha()));

        usuario.setDescricao("Olá, sou novo aqui. Esta é uma descrição temporária.");
        usuario.setFoto_de_perfil(cadastro.getFoto_de_perfil());
        usuario.setRole(UserRole.CLIENTE);

        return r_usuario.save(usuario);
    }

    /**
     * Confere a validade de um {@link UserCadastro}
     */
    public boolean validarUserCadastro(UserCadastro cadastro) {
        if (cadastro.getNome()==null) return false;
        if (cadastro.getCpf()==null) return false;
        if (cadastro.getTelefone()==null) return false;
        if (cadastro.getEmail()==null) return false;
        if (cadastro.getSenha()==null) return false;
        if (cadastro.getFoto_de_perfil()==null) return false;

        if (cadastro.getNome().isBlank()) return false;
        if (cadastro.getCpf().isBlank()) return false;
        if (cadastro.getEmail().isBlank()) return false;
        if (cadastro.getTelefone().isBlank()) return false;
        if (cadastro.getSenha().isBlank()) return false;

        // CPF
        if (cadastro.getCpf().length() != 11) return false;
        try {
            new BigDecimal(cadastro.getCpf());
        } catch (Exception e) {return false;}

        // Senha
        Matcher matcher = senha_valida.matcher(cadastro.getSenha());

        if (cadastro.getSenha().length() < 8) return false;
        if (!matcher.find()) return false;

        // TODO: Verificar Foto de perfil

        return true;
    }
}
