package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Document;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.DocumentService;

@RestController
@RequestMapping("/documentos")
public class DocumentController {

    
    private final DocumentService documentoService;

    public DocumentController(DocumentService service) {
        this.documentoService = service;
    }
    @GetMapping("/listar-Documentos")
    public List<Document> ListarDocumento() {
        return documentoService.ListarDocumento();
    }

}
