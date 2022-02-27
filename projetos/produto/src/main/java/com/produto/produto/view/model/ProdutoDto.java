package com.produto.produto.view.model;

import com.produto.produto.model.Produto;

public class ProdutoDto {
    
    private String id;
    private Integer codigo;
    private String nome;
    private Double valor;
    private Integer qtdEstoque;

    public ProdutoDto(String id, Integer codigo, String nome, Double valor, Integer qtdEstoque) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public ProdutoDto(){}

    public String getId() {
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
    
    /*public static ProdutoDto parse(Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setCodigo(produto.getCodigo()); 
        dto.setNome(produto.getNome());;
        dto.setValor(produto.getValor());; 
        dto.setQtdEstoque(produto.getQtdEstoque());
        return dto;
    }*/

    public static ProdutoDto from(Produto produto) {
        return new ProdutoDto(produto.getId(), produto.getCodigo(),
        produto.getNome(), produto.getValor(), produto.getQtdEstoque());
    }

}
