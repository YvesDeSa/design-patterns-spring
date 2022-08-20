package com.example.designpatterns.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.designpatterns.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

}