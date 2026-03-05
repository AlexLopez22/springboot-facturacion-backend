package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Serie;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
    List<Serie> findByIdDocumentos(Integer idDocumentos);
    Optional<Serie> findByIdDocumentosAndPredeterminadaTrue(Integer idDocumentos);
    Optional<Serie> findByNombreSerie(String nombreSerie);
}
