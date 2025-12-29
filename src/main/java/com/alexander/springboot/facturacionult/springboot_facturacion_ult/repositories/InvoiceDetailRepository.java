package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.InvoiceDetail;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
    
}

