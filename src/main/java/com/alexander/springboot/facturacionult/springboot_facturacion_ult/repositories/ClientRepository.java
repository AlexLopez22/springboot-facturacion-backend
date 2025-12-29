package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
   
    // Buscar por RUC o DNI
    Client findByRuc(String ruc);
    Client findByDni(String dni);
}
