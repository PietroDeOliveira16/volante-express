package com.exp_backend.volante.autenticacao.spring_security.repository;

import com.exp_backend.volante.autenticacao.spring_security.model.M_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface R_Usuario extends JpaRepository<M_Usuario,Long> {
    @Query(value = "select * from usuario where username=:USERNAME",nativeQuery = true)
    M_Usuario findByUsername(@Param(":USERNAME") String username);
}
