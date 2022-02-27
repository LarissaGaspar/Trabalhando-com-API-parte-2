package com.venda.venda.view.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="produtos", fallback = ProdutoFeignClientFallBack.class)
public interface ProdutoFeignClient {
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> obterPorId(@PathVariable String id);

    @GetMapping("/{data}")
    public String obterPorData(@PathVariable String data);

    @GetMapping("/{id}/{valor}")
    public ResponseEntity<Object> saida(@PathVariable String id, @PathVariable Integer valor);
}

@Component
class ProdutoFeignClientFallBack implements ProdutoFeignClient{

    @Override
    public String obterPorData(String data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Object> obterPorId(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> saida(String id, Integer valor) {
        return null;
    }

}
