package com.venda.venda.view.model;

import javax.validation.constraints.Min;

import javax.validation.constraints.NotBlank;

import com.venda.venda.model.Venda;

public class CadVendaDto {
    
    private String nomeDoProduto;
    @NotBlank(message = "Preencha somente com números (ddmmaaaa)")
    private String dataVenda;
    //@Min()
    private Integer qtdVendida;
    @NotBlank(message = "Preencha o ID com dados válidos.")
    private String idProduto;
    private Double vlrUnitario;
    private Double vlrTotal;

    public CadVendaDto(String nomeDoProduto, String dataVenda, Integer qtdVendida,
    String idProduto, Double vlrUnitario, Double vlrTotal) {
        this.nomeDoProduto = nomeDoProduto;
        this.dataVenda = dataVenda;
        this.qtdVendida = qtdVendida;
        this.idProduto=idProduto;
        this.vlrUnitario=vlrUnitario;
        this.vlrTotal=vlrUnitario*qtdVendida;

    }

    public String getNomeDoProduto(){
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nome){
        this.nomeDoProduto=nome;
    }

    public String getDataVenda(){
        return dataVenda;
    }

    public void setDataVenda(String dataVenda){
        this.dataVenda=dataVenda;
    }

    public Integer getQtdVendida(){
        return qtdVendida;
    }

    public void setQtdVendida(Integer qtdVendida){
        this.qtdVendida=qtdVendida;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public Double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(Double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public Double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(Double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public static CadVendaDto from(Venda venda) {
        return new CadVendaDto(venda.getNomeDoProduto(), 
        venda.getDataVenda(), venda.getQtdVendida(), venda.getIdProduto(),
        venda.getVlrUnitario(), venda.getVlrTotal());
    }

}
