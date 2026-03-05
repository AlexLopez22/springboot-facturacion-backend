package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Ubigeo;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.UbigeoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubigeos")
@CrossOrigin("*") // permite peticiones desde tu frontend
public class UbigeoController {

    private final UbigeoService ubigeoService;

    public UbigeoController(UbigeoService ubigeoService) {
        this.ubigeoService = ubigeoService;
    }

    @GetMapping
    public List<Ubigeo> getUbigeos() {
        return ubigeoService.getUbigeos();
    }
}