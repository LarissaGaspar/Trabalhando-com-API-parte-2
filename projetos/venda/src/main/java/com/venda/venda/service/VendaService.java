package com.venda.venda.service;

import java.util.List;
import java.util.Optional;

import com.venda.venda.model.Venda;

public interface VendaService {

    Venda adicionar(Venda venda);
    
    Optional<Venda> obterPorId(String id);

    List<Venda> listarTodas();

    public void deletar(String id);

    public void deletarTodas();

}
