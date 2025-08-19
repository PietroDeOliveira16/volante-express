package com.exp_backend.volante.autenticacao.spring_security.controller;

import com.exp_backend.volante.autenticacao.spring_security.model.M_Arquivo;
import com.exp_backend.volante.autenticacao.spring_security.service.S_Arquivo;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para {@link com.exp_backend.volante.autenticacao.spring_security.model.M_Arquivo}
 */
@RestController
public class C_Arquivo {

    private final S_Arquivo s_arquivo;

    public C_Arquivo(S_Arquivo s_arquivo) {
        this.s_arquivo = s_arquivo;
    }

    @GetMapping("/arquivo/{nomeArquivo}")
    public ResponseEntity<ByteArrayResource> getArquivo(@PathVariable("nomeArquivo") String nomeArquivo) {
        M_Arquivo arquivoResponse = s_arquivo.getArquivoByNome(nomeArquivo);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(arquivoResponse.getTipoArquivo())).header(
                HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivoResponse.getNomeArquivo() + "\"")
                .body(new ByteArrayResource(arquivoResponse.getDadosArquivo()));

    }
}
