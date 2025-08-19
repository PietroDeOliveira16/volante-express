package com.exp_backend.volante.autenticacao.spring_security.controller;

import com.exp_backend.volante.autenticacao.spring_security.model.UserCadastro;
import com.exp_backend.volante.autenticacao.spring_security.service.S_Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C_Usuario {

    private final S_Usuario s_usuario;

    public C_Usuario(S_Usuario s_usuario) {
        this.s_usuario = s_usuario;
    }

    /**
     * Implementação de RF02 - "CREATE Usuário"
     */
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody UserCadastro cadastro) {
        if (!s_usuario.validarUserCadastro(cadastro)) {
            return ResponseEntity.badRequest().body("Erro nos parâmetros fornecidos");
        }
        s_usuario.cadastrarUsuario(cadastro);
        return ResponseEntity.ok("Cadastrou");
    }
}
