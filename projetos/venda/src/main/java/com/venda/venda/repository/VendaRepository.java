package com.venda.venda.repository;

import java.util.Optional;

import com.venda.venda.model.Venda;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends MongoRepository<Venda,String>{

    //Optional<Venda> findAll(String data);
    
}
