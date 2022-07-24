package com.vinicius.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.api.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String>{
    


}
