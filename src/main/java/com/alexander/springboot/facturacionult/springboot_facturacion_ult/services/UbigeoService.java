package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Ubigeo;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.UbigeoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbigeoService {

    private final UbigeoRepository ubigeoRepository;

    public UbigeoService(UbigeoRepository ubigeoRepository) {
        this.ubigeoRepository = ubigeoRepository;
    }

    // Obtener todos los ubigeos
    public List<Ubigeo> getUbigeos() {
        return ubigeoRepository.findAll();
    }
}