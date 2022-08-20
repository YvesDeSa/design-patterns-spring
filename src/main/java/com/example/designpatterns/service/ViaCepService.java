package com.example.designpatterns.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.designpatterns.model.Address;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

  @GetMapping("/{cep}/json/")
  Address getCep(@PathVariable("cep") String cep);
}
