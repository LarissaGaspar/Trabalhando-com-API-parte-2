package com.venda.venda.view.model;

import java.util.List;

public class BadRequestDto {
    
    private List<String> erros;

    public BadRequestDto(List<String> erros) {
        this.erros = erros;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
