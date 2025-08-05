package com.exp_backend.volante.testes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C_Teste {
    @GetMapping("/publico-teste")
    public ResponseEntity<String> getTestePublico(){
        return ResponseEntity.ok("Teste get publico ok");
    }

    @PostMapping("/publico-teste-post:{num}")
    public ResponseEntity<String> getTestePublico(@PathVariable("num") int num){
        return ResponseEntity.ok("Teste post publico numero: "+num);
    }

    @GetMapping("/teste-get")
    public ResponseEntity<String> getTeste(){
        return ResponseEntity.ok("Teste get ok");
    }

    @PostMapping("/teste-post:{num}")
    public ResponseEntity<String> getTeste(@PathVariable("num") int num){
        return ResponseEntity.ok("Teste post ok ("+num+")");
    }
}
