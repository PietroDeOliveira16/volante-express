package com.exp_backend.volante.autenticacao.spring_security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    // AQUI É O MODEL DO USUARIO
    //private M_Usuario usuario;


    // AQUI VOCÊ COLOCA O SEGUINTE CÓDIGO DENTRO DOS PARÂMETROS DA FUNÇÃO: M_Usuario usuario
    public UserPrincipal() // <- AQUI BOTA O CÓDIGO
    {
        //this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // REMOVER COMENTÁRIOS E RETURN NULL AQUI DENTRO APÓS IMPLEMENTAR MODEL DE USUÁRIO
        /*UserRole role = usuario.getRole();
        if(role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_PROFESSOR"), new SimpleGrantedAuthority("ROLE_ALUNO"));
        else if (role == UserRole.PROFESSOR) return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                new SimpleGrantedAuthority("ROLE_ALUNO"));
        else return List.of(new SimpleGrantedAuthority("ROLE_ALUNO"));*/
        return null;
    }


    // DESCOMENTAR FUNÇÕES E REMOVER RETURN NULL QUANDO USUÁRIO ESTIVER IMPLEMENTADO
    @Override
    public String getPassword() {
        //return usuario.getPassword();
        return null;
    }

    @Override
    public String getUsername() {
        //return usuario.getUsername();
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
