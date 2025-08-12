package com.exp_backend.volante.autenticacao.spring_security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private M_Usuario usuario;

    public UserPrincipal(M_Usuario usuario)
    {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (usuario.getRole()) {
            case ADMIN -> {
                return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_SECRETARIO"),
                    new SimpleGrantedAuthority("ROLE_VENDEDOR"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"));
            }
            case SECRETARIO -> {
                return List.of(new SimpleGrantedAuthority("ROLE_SECRETARIO"));
            }
            case VENDEDOR -> {
                return List.of(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
            }
            case CLIENTE -> {
                return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
            }
            default -> {
                return null;
            }
        }

    }


    // DESCOMENTAR FUNÇÕES E REMOVER RETURN NULL QUANDO USUÁRIO ESTIVER IMPLEMENTADO
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
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
