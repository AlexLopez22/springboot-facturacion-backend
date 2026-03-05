package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Document;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.DocumentRepository;

@Service
public class DocumentService {


        private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> ListarDocumento() {
        return documentRepository.findAll();
    }
}
