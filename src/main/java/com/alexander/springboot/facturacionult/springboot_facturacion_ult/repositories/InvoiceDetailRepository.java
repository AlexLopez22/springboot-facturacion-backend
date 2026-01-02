package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
    List<InvoiceDetail> findByInvoiceId(Long invoiceId);
}
