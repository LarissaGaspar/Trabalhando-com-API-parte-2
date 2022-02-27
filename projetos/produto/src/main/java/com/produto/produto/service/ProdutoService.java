package com.produto.produto.service;

import java.util.List;
import java.util.Optional;

import com.produto.produto.model.Produto;

public interface ProdutoService {
    
    List<Produto> listarTodos();

    Produto adicionar(Produto produto);
    
    Optional<Produto> obterPorId(String id);

    /*Optional<Produto> saida(String id, Integer valor);*/

    /*Optional<Produto> obterPorData(String data);*/
}
