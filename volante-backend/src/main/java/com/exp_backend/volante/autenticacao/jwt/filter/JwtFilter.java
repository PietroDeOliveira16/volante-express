package com.exp_backend.volante.autenticacao.jwt.filter;

import com.exp_backend.volante.autenticacao.jwt.service.S_Jwt;
import com.exp_backend.volante.autenticacao.spring_security.service.CustomUserDetailsService;
import com.exp_backend.volante.autenticacao.util.AuthUtil;
import com.exp_backend.volante.autenticacao.util.SecurityParameters;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private S_Jwt s_jwt;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        Cookie[] cookies = request.getCookies();

        if(SecurityParameters.PUBLIC_ENDPOINTS.contains(path) && cookies == null){
            filterChain.doFilter(request, response);
            return;
        }

        String token = null;
        String username = null;

        token = AuthUtil.retreiveTokenFromCookies(cookies);

        if (token != null) {
            try {
                username = s_jwt.extractUsernameFromToken(token);
            }catch(Exception e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token ausente");
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);

            if (s_jwt.validateToken(token, userDetails)) {
                if(path.equals("/auth/login")){
                    response.sendError(HttpServletResponse.SC_CONFLICT, "Conta já logada!");
                    return;
                }
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
