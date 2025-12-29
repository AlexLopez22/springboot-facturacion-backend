package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @NonNull
     Optional<Product> findById(@NonNull Long id);

}
