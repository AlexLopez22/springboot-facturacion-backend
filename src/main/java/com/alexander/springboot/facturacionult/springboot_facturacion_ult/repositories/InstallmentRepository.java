package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Installment;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    
}

