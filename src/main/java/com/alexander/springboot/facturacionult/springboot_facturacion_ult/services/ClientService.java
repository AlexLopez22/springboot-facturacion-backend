package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ClientDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Address;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> listClient() {
        return clientRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public Optional<ClientDTO> listClientById(Long id) {
        return clientRepository.findById(id).map(this::mapToDTO);
    }
    // Convierte la entidad Client en un DTO para enviar datos seguros al frontend
    private ClientDTO mapToDTO(Client client) {
        return new ClientDTO(client); 
    }

    public ClientDTO createClient(ClientDTO request) {
        
        Client client = new Client();
        client.setTipoDocumento(request.getTipoDocumento());
        client.setNumeroDocumento(request.getNumeroDocumento());
        client.setRazonSocial(request.getRazonSocial());

        if (request.getDireccion() != null) {
            Address address = new Address();
            address.setDireccionCompleta(request.getDireccion().getDireccionCompleta());
            client.setDireccion(address);
        }
        Client saved = clientRepository.save(client);
        return mapToDTO(saved); 
    }
}
