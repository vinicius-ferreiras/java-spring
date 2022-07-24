package com.vinicius.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.api.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {


    
}
