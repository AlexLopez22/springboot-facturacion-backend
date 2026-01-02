package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.ClientService;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Listar clientes
    @GetMapping("/list-clients")
     public ResponseEntity<List<ClientDTO>> listClient() {
         List<ClientDTO> dtos = clientService.listClient();
         return ResponseEntity.ok(dtos);
    }
     
     // Obtener cliente por ID
     @GetMapping("/list-clients/{id}")
     public ResponseEntity<ClientDTO> listClientById(@PathVariable Long id) {
         return clientService.listClientById(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
     }
     
     @PostMapping("/create-client")
     public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO request) {
         ClientDTO dto = clientService.createClient(request);
     return ResponseEntity.ok(dto);
 }
}
