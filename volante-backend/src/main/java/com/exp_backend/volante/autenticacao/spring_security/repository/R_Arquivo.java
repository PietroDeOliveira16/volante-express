package com.exp_backend.volante.autenticacao.spring_security.repository;

import com.exp_backend.volante.autenticacao.spring_security.model.M_Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Arquivo extends JpaRepository<M_Arquivo, Long> {
    @Query(value = "select * from arquivo where nomeArquivo=:NOME limit 1",nativeQuery = true)
    M_Arquivo getArquivoByNome(@Param("NOME") String nomeArquivo);
}
