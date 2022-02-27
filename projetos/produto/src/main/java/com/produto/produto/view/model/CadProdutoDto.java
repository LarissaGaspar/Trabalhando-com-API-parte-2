package com.produto.produto.view.model;

import javax.validation.constraints.NotBlank;

import com.produto.produto.model.Produto;

public class CadProdutoDto {
    
    //private String id;
    //@NotBlank(message = "Preencha a marca com dados válidos.")
    private Integer codigo;//aqui o id não e manipulado
    @NotBlank(message = "Preencha a marca com dados válidos.")
    private String nome;
    //@NotBlank(message = "Preencha a marca com dados válidos.")
    private Double valor;
    //@NotBlank(message = "Preencha a marca com dados válidos.")
    private Integer qtdEstoque;

    public CadProdutoDto(Integer codigo, String nome, Double valor, Integer qtdEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public CadProdutoDto() {}

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
    
    public static CadProdutoDto from(Produto produto) {
        return new CadProdutoDto(produto.getCodigo(), 
        produto.getNome(),produto.getValor(), produto.getQtdEstoque());
    }
}
