package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    @Override
    @EntityGraph(attributePaths = {"items", "totales", "cliente", "emisor", "formaPago"})
    @NonNull
    List<Invoice> findAll();
    
    @Override
    @EntityGraph(attributePaths = {"items", "totales", "cliente", "emisor", "formaPago"})
    @NonNull
    Optional<Invoice> findById(@NonNull Long id);



    
}
