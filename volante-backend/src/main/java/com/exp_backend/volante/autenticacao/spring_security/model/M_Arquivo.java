package com.exp_backend.volante.autenticacao.spring_security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Modelo que representa uma imagem
 */
@Entity
@Table(name = "arquivo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class M_Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_autor_upload")
    private M_Usuario autorUpload;

    private LocalDateTime dataUpload;

    private String nomeArquivo;

    private String tipoArquivo;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "dados_arquivo")
    private byte[] dadosArquivo;

    private Long tamanhoArquivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public M_Usuario getAutorUpload() {
        return autorUpload;
    }

    public void setAutorUpload(M_Usuario autorUpload) {
        this.autorUpload = autorUpload;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public byte[] getDadosArquivo() {
        return dadosArquivo;
    }

    public void setDadosArquivo(byte[] dadosArquivo) {
        this.dadosArquivo = dadosArquivo;
    }

    public Long getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(Long tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }
}
