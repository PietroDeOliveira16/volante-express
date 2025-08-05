package com.exp_backend.volante.autenticacao.spring_security.config;

import com.exp_backend.volante.autenticacao.jwt.filter.JwtFilter;
import com.exp_backend.volante.autenticacao.util.SecurityParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    ClearSiteDataHeaderWriter headerWriter = new ClearSiteDataHeaderWriter(
            ClearSiteDataHeaderWriter.Directive.COOKIES, ClearSiteDataHeaderWriter.Directive.STORAGE, ClearSiteDataHeaderWriter.Directive.CACHE
    );
    HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(headerWriter);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                // Permite os endpoints da lista de endpoints publicos a fazer requisições sem login e cargo
                                SecurityParameters.PUBLIC_ENDPOINTS.toArray(new String[0])
                        ).permitAll()
                        // Tire o comentário do requestMatchers para habilitar os endpoints que dependem de login e cargo
                        // TIRE O COMENTÁRIO APENAS SE A LISTA DE ENDPOINTS ESTIVER COM ALGUM CONTEÚDO,
                        // POIS DÁ ERRO SE A LISTA ESTIVER VAZIA
                        // SE ALGUMA LISTA ESTIVER VAZIA, COMENTE A LINHA DESSA LISTA
                        .requestMatchers(HttpMethod.POST, SecurityParameters.ADMIN_POST_ENDPOINTS.toArray(new String[0])).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, SecurityParameters.ADMIN_GET_ENDPOINTS.toArray(new String[0])).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, SecurityParameters.VENDEDOR_POST_ENDPOINTS.toArray(new String[0])).hasRole("VENDEDOR")
                        .requestMatchers(HttpMethod.GET, SecurityParameters.VENDEDOR_GET_ENDPOINTS.toArray(new String[0])).hasRole("VENDEDOR")
                        .requestMatchers(HttpMethod.POST, SecurityParameters.SECRETARIO_POST_ENDPOINTS.toArray(new String[0])).hasRole("SECRETARIO")
                        .requestMatchers(HttpMethod.GET, SecurityParameters.SECRETARIO_GET_ENDPOINTS.toArray(new String[0])).hasRole("SECRETARIO")
                        .requestMatchers(HttpMethod.POST, SecurityParameters.CLIENTE_POST_ENDPOINTS.toArray(new String[0])).hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, SecurityParameters.CLIENTE_GET_ENDPOINTS.toArray(new String[0])).hasRole("CLIENTE")
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        // endpoint que o frontend deve chamar para dar logout
                        // (não precisa de controller, o spring security já "cria" um controller automatico de logout do backend)
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))
                        .invalidateHttpSession(true)
                        .deleteCookies("sessionToken", "sessionCookie", "userCookie")
                        .addLogoutHandler(clearSiteData)
                )
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge((SecurityParameters.TOKEN_COOKIE_LONG_MAX_AGE_SECS));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(SecurityParameters.ENCODER_STRENGTH));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
