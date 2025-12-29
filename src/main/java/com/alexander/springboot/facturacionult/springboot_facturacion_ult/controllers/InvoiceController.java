package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceFullDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Invoice;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceDTO> listInvoices() {
        return invoiceService.listInvoices();
    }
    // Detalle completo de una factura
    @GetMapping("/{id}")
    public InvoiceFullDTO getInvoice(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping
    public ResponseEntity<String> createInvoice(@RequestBody Invoice invoice) {
        Invoice saved = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok("Factura creada correctamente: Id de la factura " + saved.getId());
    }
}
