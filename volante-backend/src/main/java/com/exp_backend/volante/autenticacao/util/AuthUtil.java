package com.exp_backend.volante.autenticacao.util;

import com.exp_backend.volante.autenticacao.jwt.service.S_Jwt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUtil {
    @Autowired
    private S_Jwt s_jwt;

    // AQUI VAI O REPOSITORY DO MODEL DE USUÁRIO
    /*@Autowired
    private R_Usuario r_usuario;*/

    public static String retreiveTokenFromCookies(Cookie[] cookies){
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    // TIRAR ESTA FUNÇÃO DO COMENTÁRIO QUANDO MODEL E REPOSITORY DE USUARIO ESTIVER IMPLEMENTADO
   /* public M_Usuario findUsuarioWithRequest(HttpServletRequest request){
        String token = AuthUtil.retreiveTokenFromCookies(request.getCookies());
        String username = s_jwt.extractUsernameFromToken(token);
        return r_usuario.findByUsername(username);
    }*/
}
