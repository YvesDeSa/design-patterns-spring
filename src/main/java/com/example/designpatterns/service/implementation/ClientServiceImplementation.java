package com.example.designpatterns.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.designpatterns.model.Address;
import com.example.designpatterns.model.Client;
import com.example.designpatterns.model.repositories.AddressRepository;
import com.example.designpatterns.model.repositories.ClientRepository;
import com.example.designpatterns.service.ClientService;
import com.example.designpatterns.service.ViaCepService;

@Service
public class ClientServiceImplementation implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ViaCepService viaCepService;

  @Override
  public Iterable<Client> getAll() {
    return clientRepository.findAll();
  }

  @Override
  public Client getById(Long id) {
    Optional<Client> client = clientRepository.findById(id);
    return client.get();
  }

  @Override
  public void insert(Client client) {
    saveClientWithCep(client);
  }

  @Override
  public void update(Long id, Client client) {
    Optional<Client> clientBD = clientRepository.findById(id);
    if (clientBD.isPresent()) {
      saveClientWithCep(client);
    }
  }

  @Override
  public void delete(Long id) {
    clientRepository.deleteById(id);
  }

  private void saveClientWithCep(Client client) {
    String cep = client.getAddress().getCep();
    Address address = addressRepository.findById(cep).orElseGet(() -> {
      Address newAddress = viaCepService.getCep(cep);
      addressRepository.save(newAddress);
      return newAddress;
    });
    client.setAddress(address);

    clientRepository.save(client);
  }

}
