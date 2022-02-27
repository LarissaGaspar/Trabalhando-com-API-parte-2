package com.venda.venda.service;

import java.util.List;
import java.util.Optional;

import com.venda.venda.model.Venda;
import com.venda.venda.repository.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaServiceImpl implements VendaService{
    
    @Autowired
    private VendaRepository repositorio;

    @Override
    public Venda adicionar(Venda venda) {
        return repositorio.save(venda);
    }

    @Override
    public Optional<Venda> obterPorId(String id) {
        return repositorio.findById(id);
    }

    @Override
    public List<Venda> listarTodas() {
        return repositorio.findAll();
    }

    @Override
    public void deletar(String id){
        repositorio.deleteById(id);
    }

    @Override
    public void deletarTodas(){
        repositorio.deleteAll();;
    }

}
