package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceFullDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/list-invoices")
    public ResponseEntity<List<InvoiceFullDTO>> listInvoices() {
        return ResponseEntity.ok(invoiceService.listInvoices());
}


    @GetMapping("/list-invoices/{id}")
    public ResponseEntity<InvoiceFullDTO> listInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.listInvoiceById(id));
    }

    @PostMapping("/create-invoices")
    public ResponseEntity<InvoiceFullDTO> createInvoice(@RequestBody InvoiceDTO dto) {
        InvoiceFullDTO created = invoiceService.createInvoice(dto);
        return ResponseEntity.ok(created);
    }
}
