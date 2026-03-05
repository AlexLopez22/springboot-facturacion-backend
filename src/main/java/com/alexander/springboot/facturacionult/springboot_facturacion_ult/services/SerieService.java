package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Serie;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.SerieRepository;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<Serie> listar() {
        return serieRepository.findAll();
    }

    public Serie obtenerPredeterminada(Integer documentoId) {
        return serieRepository
                .findByIdDocumentosAndPredeterminadaTrue(documentoId)
                .orElse(null);
    }

    public Serie guardar(Serie serie) {

        if (Boolean.TRUE.equals(serie.getPredeterminada())) {

            Serie actual = serieRepository
                    .findByIdDocumentosAndPredeterminadaTrue(serie.getIdDocumentos())
                    .orElse(null);

            if (actual != null && !actual.getId().equals(serie.getId())) {
                actual.setPredeterminada(false);
                serieRepository.save(actual);
            }
        }

        return serieRepository.save(serie);
    }
}