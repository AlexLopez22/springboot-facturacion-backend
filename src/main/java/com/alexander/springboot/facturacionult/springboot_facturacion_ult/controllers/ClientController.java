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

    @GetMapping("/sunat/{ruc}")
    public ResponseEntity<ClientDTO> consultarRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(clientService.consultarDatosSunat(ruc));
    }
    @GetMapping("/reniec/{dni}")
    public ClientDTO consultarDni(@PathVariable String dni) {
        return clientService.consultarDatosReniec(dni);
}
     
    @PostMapping("/create-client")
    public ClientDTO guardar(@RequestBody ClientDTO dto) {
        return clientService.guardar(dto);
    }
}
