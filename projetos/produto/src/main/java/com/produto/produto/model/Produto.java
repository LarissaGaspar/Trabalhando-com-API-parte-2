package com.produto.produto.model;

import java.util.Optional;

import com.produto.produto.view.model.CadProdutoDto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("produtos")
public class Produto {
    
    @Id
    private String id;//nao entra no construtor sera gerado pelo banco
    private Integer codigo;
    private String nome;
    private Double valor;
    private Integer qtdEstoque;

    public Produto(Integer codigo, String nome, Double valor, Integer qtdEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto() {}

    public String getId() {//mas tem get e set
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCodigo(){
        return codigo;
    }

    public void setCodigo(Integer codigo){//o que a gente receber, entrará no atributo da classe
        this.codigo=codigo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public Double getValor(){
        return valor;
    }

    public void setValor(Double valor){
        this.valor=valor;
    }

    public Integer getQtdEstoque(){
        return qtdEstoque;
    }

    public Integer setQtdEstoque(Integer qtdSaida){
        this.qtdEstoque = qtdEstoque - qtdSaida;
        return qtdEstoque;
    }
    
    public static Produto from(CadProdutoDto dto) {
        return new Produto(dto.getCodigo(), dto.getNome(),
        dto.getValor(), dto.getQtdEstoque());
    }

    /*public Produto from(Optional<Produto> produto){
        Produto p = new Produto();
        p.setId(produto.getId());
        p.setCodigo(produto.getCodigo()); 
        p.setNome(produto.getNome());;
        p.setValor(produto.getValor());; 
        p.setQtdEstoque(produto.getQtdEstoque());
        return new Produto();
    }*/
}
