package com.exp_backend.volante.autenticacao.spring_security.service;

import com.exp_backend.volante.autenticacao.spring_security.model.M_Usuario;
import com.exp_backend.volante.autenticacao.spring_security.model.UserPrincipal;
import com.exp_backend.volante.autenticacao.spring_security.repository.R_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service para {@link UserPrincipal}
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // AQUI É O REPOSITORY DO MODEL DO USUÁRIO
    @Autowired
    private R_Usuario r_usuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // AQUI É AUTO EXPLICATIVO. REMOVER RETURN NULL DEPOIS QUE AS CLASSES FOREM IMPLEMENTADAS

        M_Usuario usuario = r_usuario.findByUsername(username);

        if(usuario == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(usuario);
    }
}
