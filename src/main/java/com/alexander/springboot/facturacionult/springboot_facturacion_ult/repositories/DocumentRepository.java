package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findByNombre(String nombre);

}
