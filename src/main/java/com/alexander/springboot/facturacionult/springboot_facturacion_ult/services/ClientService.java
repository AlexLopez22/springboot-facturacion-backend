package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client registerClient(Client client) {
        // Aquí podrías validar RUC/DNI antes de guardar
        return clientRepository.save(client);
    }

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    public Client getClientByRuc(String ruc) {
        return clientRepository.findByRuc(ruc);
    }

    public Client getClientByDni(String dni) {
        return clientRepository.findByDni(dni);
    }
}
