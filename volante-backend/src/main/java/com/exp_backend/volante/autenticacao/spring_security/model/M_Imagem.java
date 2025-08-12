package com.exp_backend.volante.autenticacao.spring_security.model;

import jakarta.persistence.*;

/**
 * Modelo que representa uma imagem
 */
@Entity
@Table(name="imagem")
public class M_Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 7)
    private String formato;

    //TODO: ADICIONAR BLOB DE IMAGEM
}
