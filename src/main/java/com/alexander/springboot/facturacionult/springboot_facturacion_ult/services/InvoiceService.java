package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InstallmentDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDetailDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceFullDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Installment;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Invoice;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.InvoiceDetail;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(Invoice invoice) {
        invoice.setFechaRegistro(LocalDateTime.now());
        invoice.setFechaCreacion(LocalDateTime.now());
        invoice.setFechaActualizacion(LocalDateTime.now());

        // Vincular cada detalle con la factura
        if (invoice.getDetalles() != null) {
            for (InvoiceDetail detail : invoice.getDetalles()) {
                detail.setFactura(invoice);
            }
        }

        // Vincular cada cuota con la factura
        if (invoice.getCuotas() != null) {
            for (Installment installment : invoice.getCuotas()) {
                installment.setFactura(invoice);
            }
        }

        return invoiceRepository.save(invoice);
    }

    public List<InvoiceDTO> listInvoices() {
        return invoiceRepository.findAll().stream()
            .map(inv -> {
                InvoiceDTO dto = new InvoiceDTO();
                dto.setId(inv.getId());
                dto.setClienteId(inv.getClienteId());
                dto.setTotal(inv.getTotal());
                dto.setEstado(inv.getEstado());
                dto.setCondicionPago(inv.getCondicionPago());
                return dto;
            })
            .toList();
    }

    @Transactional(readOnly = true)
    public InvoiceFullDTO getInvoiceById(Long id) {
        Invoice inv = invoiceRepository.findById(id) 
        .orElseThrow(() -> new RuntimeException("No se ha encontrado la factura"));

        InvoiceFullDTO dto = new InvoiceFullDTO();
        dto.setId(inv.getId());
        dto.setFechaEmision(inv.getFechaEmision());
        dto.setFechaRegistro(inv.getFechaRegistro());
        dto.setClienteId(inv.getClienteId());
        dto.setCondicionPago(inv.getCondicionPago());
        dto.setTipoDocumento(inv.getTipoDocumento());
        dto.setMoneda(inv.getMoneda());
        dto.setTipoOperacion(inv.getTipoOperacion());
        dto.setSerie(inv.getSerie());
        dto.setCorrelativo(inv.getCorrelativo());
        dto.setSubtotal(inv.getSubtotal());
        dto.setIgvTotal(inv.getIgvTotal());
        dto.setTotal(inv.getTotal());
        dto.setTipoCambio(inv.getTipoCambio());
        dto.setEstado(inv.getEstado());
        dto.setFechaCreacion(inv.getFechaCreacion());
        dto.setFechaActualiacion(inv.getFechaActualizacion());

        if (inv.getDetalles() != null) {
            dto.setDetalles(inv.getDetalles().stream().map(d -> {
                InvoiceDetailDTO ddto = new InvoiceDetailDTO();
                ddto.setProductoId(d.getProductoId());
                ddto.setCantidad(d.getCantidad());
                ddto.setPrecioUnitario(d.getPrecioUnitario());
                ddto.setValorVenta(d.getValorVenta());
                ddto.setIgv(d.getIgv());
                ddto.setTotalLinea(d.getTotalLinea());
                return ddto;
            }).toList());
        }

        if (inv.getCuotas() != null) {
            dto.setCuotas(inv.getCuotas().stream().map(c -> {
                InstallmentDTO cdto = new InstallmentDTO();
                cdto.setNumero(c.getNumero());
                cdto.setFechaVencimiento(c.getFechaVencimiento());
                cdto.setMonto(c.getMonto());
                return cdto;
            }).toList());
        }

        return dto;
    }
}
