package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbigeoRepository extends JpaRepository<Ubigeo, String> {
}