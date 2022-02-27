package com.venda.venda.model;


import com.venda.venda.view.model.CadVendaDto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("vendas")
public class Venda {
    
    @Id
    private String id;
    private String nomeDoProduto;
    private String dataVenda;
    private Integer qtdVendida;
    private String idProduto;
    private Double vlrUnitario;
    private Double vlrTotal;

    public Venda(String nomeDoProduto, String dataVenda, Integer qtdVendida, String idProduto,
    Double vlrUnitario, Double vlrTotal) {
        this.nomeDoProduto = nomeDoProduto;
        this.dataVenda = dataVenda;
        this.qtdVendida = qtdVendida;
        this.idProduto=idProduto;
        this.vlrUnitario=vlrUnitario;
        this.vlrTotal=vlrUnitario*qtdVendida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public static Venda from(CadVendaDto dto) {
        return new Venda(dto.getNomeDoProduto(),dto.getDataVenda(), 
        dto.getQtdVendida(), dto.getIdProduto(), dto.getVlrUnitario(),
        dto.getVlrTotal());
    }
    

}
