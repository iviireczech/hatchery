package com.unicorn.hatchery.clientapp.service;

import com.unicorn.hatchery.clientapp.domain.Client;
import com.unicorn.hatchery.clientapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client readClient(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Override
    public void updateClient(Long clientId, Client client) {
        Client clientFromDB = clientRepository.findById(clientId).orElseThrow(
                IllegalArgumentException::new
        );
        clientFromDB.setName(client.getName());
        clientFromDB.setEmail(client.getEmail());
        clientRepository.save(clientFromDB);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public List<Client> getClients() {
        return StreamSupport
                .stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
