package com.exp_backend.volante.autenticacao.spring_security.service;

import com.exp_backend.volante.autenticacao.spring_security.model.M_Arquivo;
import com.exp_backend.volante.autenticacao.spring_security.repository.R_Arquivo;
import org.springframework.stereotype.Service;

/**
 * Service para tabela {@link com.exp_backend.volante.autenticacao.spring_security.model.M_Arquivo}
 */
@Service
public class S_Arquivo {

    private final R_Arquivo r_arquivo;


    public S_Arquivo(R_Arquivo r_arquivo) {
        this.r_arquivo = r_arquivo;
    }

    /**
     * Obtêm um arquivo pelo parâmetro nomeArquivo da tabela
     */
    public M_Arquivo getArquivoByNome(String nomeArquivo) {
        return r_arquivo.getArquivoByNome(nomeArquivo);
    }
}
