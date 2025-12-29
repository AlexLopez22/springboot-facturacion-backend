package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Unprotected health endpoint just to confirm app is alive. */
@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Se ha conectado correctamente a la BD ");
    }
}
