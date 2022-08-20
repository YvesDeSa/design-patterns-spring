package com.example.designpatterns.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.designpatterns.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
