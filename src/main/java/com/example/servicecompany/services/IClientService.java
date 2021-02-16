package com.example.servicecompany.services;

import com.example.servicecompany.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {

    public List<Client> findAllClients();

    public Page<Client> findAllClients(Pageable pageable);

    public Client findById(Long id);

    public Client save(Client client);

    public void delete(Long id);

    public Client update(Long id, Client client);
}
