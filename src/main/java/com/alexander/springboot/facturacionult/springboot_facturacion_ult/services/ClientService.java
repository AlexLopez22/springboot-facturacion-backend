package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.AddressDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ClientDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Address;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.ClientRepository;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public ClientDTO consultarDatosReniec(String dni) {

        String url = "https://api.apis.net.pe/v1/dni?numero=" + dni;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error consultando RENIEC");
        }

        JSONObject json = new JSONObject(response.getBody());

        String nombres = json.optString("nombres");
        String apePat = json.optString("apellidoPaterno");
        String apeMat = json.optString("apellidoMaterno");

        String nombreCompleto = (nombres + " " + apePat + " " + apeMat).trim();

        ClientDTO dto = new ClientDTO();
        dto.setTipoDocumento("1");
        dto.setNumeroDocumento(dni);
        dto.setRazonSocial(nombreCompleto);

        return dto;
    }

    public ClientDTO consultarDatosSunat(String ruc) {

        String url = "https://api.apis.net.pe/v1/ruc?numero=" + ruc;
        String token = "TU_TOKEN_REAL";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error consultando SUNAT");
        }

        JSONObject json = new JSONObject(response.getBody());

        ClientDTO dto = new ClientDTO();
        dto.setTipoDocumento("6");
        dto.setNumeroDocumento(ruc);

        dto.setRazonSocial(json.optString("nombre"));
        dto.setEstado(json.optString("estado"));
        dto.setCondicion(json.optString("condicion"));
        dto.setUbigeo(json.optString("ubigeo"));
        dto.setViaTipo(json.optString("viaTipo"));
        dto.setViaNombre(json.optString("viaNombre"));
        dto.setZonaTipo(json.optString("zonaTipo"));
        dto.setNombre(json.optString("zonaNombre")); // o nombre comercial según tu DTO

        AddressDTO address = new AddressDTO();
        address.setDireccionCompleta(json.optString("direccion"));
        dto.setDireccion(address);

        return dto;
    }

    public ClientDTO guardar(ClientDTO dto) {

        Client client = new Client();

        client.setTipoDocumento(dto.getTipoDocumento());
        client.setNumeroDocumento(dto.getNumeroDocumento());
        client.setRazonSocial(dto.getRazonSocial());

        // 📍 Dirección
        if (dto.getDireccion() != null) {
            Address address = new Address();
            address.setDireccionCompleta(dto.getDireccion().getDireccionCompleta());
            client.setDireccion(address);
        }

        // 📍 Campos SUNAT opcionales
        client.setEstado(dto.getEstado());
        client.setCondicion(dto.getCondicion());
        client.setUbigeo(dto.getUbigeo());
        client.setViaTipo(dto.getViaTipo());
        client.setViaNombre(dto.getViaNombre());
        client.setZonaTipo(dto.getZonaTipo());

        Client saved = clientRepository.save(client);

        return new ClientDTO(saved);
    }
}
