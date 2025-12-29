package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // Sobrescribimos findById para que cargue detalles  y cuotas con EntityGraph
    @Override
    @EntityGraph(attributePaths = {"detalles", "cuotas"})
    @NonNull
    Optional<Invoice> findById(@NonNull Long id);
}
