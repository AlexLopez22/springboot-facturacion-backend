package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Serie;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.SerieRepository;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.InvoiceService;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.SerieService;

@RestController
@RequestMapping("/serie")
@CrossOrigin("*")
public class SerieController {

    private final SerieService serieService;
    private final SerieRepository repository;
    private final InvoiceService invoiceService;
    private final SerieRepository serieRepository;

    public SerieController(SerieService service, SerieRepository repository, InvoiceService invoiceService, SerieRepository serieRepository) {
        this.serieService = service;
        this.repository = repository;
        this.invoiceService = invoiceService;
        this.serieRepository = serieRepository;
    }

    @GetMapping("/listar")
    public List<Serie> listar() {
        return serieService.listar();
    }

    @GetMapping("/por-documento/{idDocumento}")
    public List<Serie> porDocumento(@PathVariable Integer idDocumento) {
        return repository.findByIdDocumentos(idDocumento);
    }

    @GetMapping("/predeterminada/{idDocumento}")
    public ResponseEntity<?> obtenerSeriePredeterminada(@PathVariable Integer idDocumento) {
        Serie serie = serieService.obtenerPredeterminada(idDocumento);
        // Si no se encuentra una serie predeterminada, devolver un 404
        if (serie == null) {
            return ResponseEntity.notFound().build();
        }
        // Obtener el siguiente correlativo para la serie predeterminada
        int numero = invoiceService.obtenerSiguienteNumero(serie.getNombreSerie());

        // Construir la respuesta con el nombre de la serie y el siguiente correlativo
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombreSerie", serie.getNombreSerie());
        respuesta.put("numero", numero);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/validar/{nombreSerie}")
    public ResponseEntity<Map<String, Boolean>> validarSerie(@PathVariable String nombreSerie) {
        boolean existe = serieRepository.findByNombreSerie(nombreSerie).isPresent();
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/crear")
    public Serie crear(@RequestBody Serie serie) {
        return serieService.guardar(serie);
    }
}