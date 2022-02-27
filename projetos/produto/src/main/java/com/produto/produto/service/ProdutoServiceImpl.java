package com.produto.produto.service;

import java.util.List;
import java.util.Optional;

import com.produto.produto.model.Produto;
import com.produto.produto.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService{
    
    @Autowired
    private ProdutoRepository repositorio;

    @Override
    public Produto adicionar(Produto produto) {
        return repositorio.save(produto);
    }

    @Override
    public Optional<Produto> obterPorId(String id) {
        return repositorio.findById(id);
    }

    @Override
    public List<Produto> listarTodos() {
        return repositorio.findAll();
    }

    /*@Override
    public Optional<Produto> obterPorData(String data) {
        return repositorio.findById(data);
    }*/
    
}
