package com.exp_backend.volante.autenticacao.util;

import java.util.Arrays;
import java.util.List;

public class SecurityParameters {

    // Força do codificador do token de sessão (12 é o recomendado pois não é muito grande e é bem seguro)
    public static final int ENCODER_STRENGTH = 12;

    // Coloque uma string com um endpoint qualquer (POST ou GET ou etc) que pode ser acessado sem login
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/publico-teste",
            "/publico-teste-post"
    );

    // Coloque uma string com um endpoint POST que pode ser acessado apenas por um admin
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> ADMIN_POST_ENDPOINTS = Arrays.asList(
            "/teste-post"
    );

    // Coloque uma string com um endpoint GET que pode ser acessado apenas por um admin
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> ADMIN_GET_ENDPOINTS = Arrays.asList(
            "/teste-get"
    );

    // Coloque uma string com um endpoint POST que pode ser acessado apenas por um vendedor
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> VENDEDOR_POST_ENDPOINTS = Arrays.asList(
            "/teste-post"
    );

    // Coloque uma string com um endpoint GET que pode ser acessado apenas por um vendedor
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> VENDEDOR_GET_ENDPOINTS = Arrays.asList(
            "/teste-get"
    );

    // Coloque uma string com um endpoint POST que pode ser acessado apenas por um secretário
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> SECRETARIO_POST_ENDPOINTS = Arrays.asList(
            "/teste-post"
    );

    // Coloque uma string com um endpoint GET que pode ser acessado apenas por um secretário
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> SECRETARIO_GET_ENDPOINTS = Arrays.asList(
            "/teste-get"
    );

    // Coloque uma string com um endpoint POST que pode ser acessado apenas por um cliente
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> CLIENTE_POST_ENDPOINTS = Arrays.asList(
            "/teste-post"
    );

    // Coloque uma string com um endpoint GET que pode ser acessado apenas por um cliente
    // Para adicionar mais endpoints, coloque uma virgula entre cada string
    public static final List<String> CLIENTE_GET_ENDPOINTS = Arrays.asList(
            "/teste-get"
    );

    // Variaveis do tempo de vida do cookie que carrega o token de sessão
    private static final long horasPorDia = 24L;
    private static final long minutosPorHora = 60L;
    private static final long segundosPorMinuto = 60L;

    // Tempo de vida do cookie EM SEGUNDOS DO TIPO LONG
    public static final long TOKEN_COOKIE_LONG_MAX_AGE_SECS = (horasPorDia * minutosPorHora * segundosPorMinuto);

    // Tempo de vida do cookie EM SEGUNDOS DO TIPO INT
    public static final int TOKEN_COOKIE_INT_MAX_AGE_SECS = ((int) TOKEN_COOKIE_LONG_MAX_AGE_SECS);

    // Tempo de vida do cookie EM MILISEGUNDOS
    public static final long TOKEN_COOKIE_MAX_AGE_MILIS = (TOKEN_COOKIE_LONG_MAX_AGE_SECS * 1000);
}
