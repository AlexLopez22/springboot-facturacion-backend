package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.AddressDTO;
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

    public List<ClientDTO> listClient() {
        return clientRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<ClientDTO> listClientById(Long id) {
        return clientRepository.findById(id)
                .map(this::mapToDTO);
    }
    
    private ClientDTO mapToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setTipoDocumento(client.getTipoDocumento());
        dto.setNumeroDocumento(client.getNumeroDocumento());
        dto.setRazonSocial(client.getRazonSocial());

        if (client.getDireccion() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setDireccionCompleta(client.getDireccion().getDireccionCompleta());
            dto.setDireccion(addressDTO);
        }
    
        return dto;
    }
}
