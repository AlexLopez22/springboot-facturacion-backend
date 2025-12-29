package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.ClientService;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> registerClient(@RequestBody ClientDTO request) {

        Client client = new Client();
        client.setRuc(request.getRuc());
        client.setDni(request.getDni());
        client.setNombreRazonSocial(request.getNombreRazonSocial());
        client.setDireccion(request.getDireccion());
        client.setCelular(request.getCelular());

        Client saved = clientService.registerClient(client);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Client>> listClient() {
        return ResponseEntity.ok(clientService.listClients());
    }
}
