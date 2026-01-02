package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDetailDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceFullDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.AddressDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ClientDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.IssuerDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.PaymentMethodDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.TotalesDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Invoice;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.InvoiceDetail;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Issuer;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.PaymentMethod;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Product;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Totales;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    // Crear factura completa
    public InvoiceFullDTO createInvoice(InvoiceDTO dto) {
    Invoice invoice = new Invoice();
    invoice.setTipoComprobante(dto.getTipoComprobante());
    invoice.setSerie(dto.getSerie());
    invoice.setNumero(dto.getNumero());
    invoice.setMoneda(dto.getMoneda());
    invoice.setTipoOperacion(dto.getTipoOperacion());

    // 👇 Aquí está el fix
    invoice.setFechaEmision(LocalDate.parse(dto.getFechaEmision()));
    invoice.setHoraEmision(LocalTime.parse(dto.getHoraEmision()));

    // Relacionar cliente, emisor y forma de pago
    Client client = new Client();
    client.setId(dto.getClienteId());
    invoice.setCliente(client);

    Issuer issuer = new Issuer();
    issuer.setId(dto.getEmisorId());
    invoice.setEmisor(issuer);

    if (dto.getFormaPagoId() != null) {
        PaymentMethod pm = new PaymentMethod();
        pm.setId(dto.getFormaPagoId());
        invoice.setFormaPago(pm);
    }

    // Totales
    if (dto.getTotales() != null) {
        Totales totales = new Totales();
        totales.setOpGravada(dto.getTotales().getOpGravada());
        totales.setOpExonerada(dto.getTotales().getOpExonerada());
        totales.setOpInafecta(dto.getTotales().getOpInafecta());
        totales.setOpGratuita(dto.getTotales().getOpGratuita());
        totales.setIgv(dto.getTotales().getIgv());
        totales.setTotalImpuestos(dto.getTotales().getTotalImpuestos());
        totales.setImporteTotal(dto.getTotales().getImporteTotal());
        invoice.setTotales(totales);
    }

    // Items
    if (dto.getItems() != null) {
        List<InvoiceDetail> details = dto.getItems().stream().map(d -> {
            InvoiceDetail detail = new InvoiceDetail();
            Product product = new Product();
            product.setId(d.getProductoId());
            detail.setProducto(product);
        detail.setItem(d.getItem());
            detail.setCodigoProducto(d.getCodigoProducto()); 
            detail.setDescripcion(d.getDescripcion());
            detail.setCantidad(d.getCantidad());
            detail.setUnidadMedida(d.getUnidadMedida());
            detail.setPrecioUnitario(d.getPrecioUnitario());
            detail.setValorUnitario(d.getValorUnitario());
            detail.setValorVenta(d.getValorVenta());
            detail.setAfectacionIgv(d.getAfectacionIgv());
            detail.setImporteTotal(d.getImporteTotal());
            detail.setItem(d.getItem());
            detail.setInvoice(invoice);
            return detail;
        }).toList();
        invoice.setItems(details);
        
    }

    Invoice saved = invoiceRepository.save(invoice);
    return listInvoiceById(saved.getId());
}

    // Listar facturas
    public List<InvoiceDTO> listInvoices() {
        return invoiceRepository.findAll().stream()
            .map(inv -> {
                InvoiceDTO dto = new InvoiceDTO();
                dto.setId(inv.getId());
                dto.setTipoComprobante(inv.getTipoComprobante());
                dto.setSerie(inv.getSerie());
                dto.setNumero(inv.getNumero());
                dto.setMoneda(inv.getMoneda());
                dto.setTipoOperacion(inv.getTipoOperacion());
                dto.setClienteId(inv.getCliente().getId());
                dto.setEmisorId(inv.getEmisor().getId());
                dto.setFormaPagoId(inv.getFormaPago() != null ? inv.getFormaPago().getId() : null);
    
                // Fechas como String
                dto.setFechaEmision(inv.getFechaEmision() != null ? inv.getFechaEmision().toString() : null);
                dto.setHoraEmision(inv.getHoraEmision() != null ? inv.getHoraEmision().toString() : null);
    
                // Totales
                if (inv.getTotales() != null) {
                    TotalesDTO totalesDTO = new TotalesDTO();
                    totalesDTO.setId(inv.getId());
                    totalesDTO.setOpGravada(inv.getTotales().getOpGravada());
                    totalesDTO.setOpExonerada(inv.getTotales().getOpExonerada());
                    totalesDTO.setOpInafecta(inv.getTotales().getOpInafecta());
                    totalesDTO.setOpGratuita(inv.getTotales().getOpGratuita());
                    totalesDTO.setIgv(inv.getTotales().getIgv());
                    totalesDTO.setTotalImpuestos(inv.getTotales().getTotalImpuestos());
                    totalesDTO.setImporteTotal(inv.getTotales().getImporteTotal());
                    dto.setTotales(totalesDTO);
                }
    
                // Items
                if (inv.getItems() != null) {
                    dto.setItems(inv.getItems().stream().map(d -> {
                        InvoiceDetailDTO ddto = new InvoiceDetailDTO();
                        ddto.setId(d.getId());
                        ddto.setProductoId(d.getProducto().getId());
                        ddto.setCodigoProducto(d.getCodigoProducto());
                        ddto.setDescripcion(d.getDescripcion());
                        ddto.setCantidad(d.getCantidad());
                        ddto.setUnidadMedida(d.getUnidadMedida());
                        ddto.setPrecioUnitario(d.getPrecioUnitario());
                        ddto.setValorUnitario(d.getValorUnitario());
                        ddto.setValorVenta(d.getValorVenta());
                        ddto.setAfectacionIgv(d.getAfectacionIgv());
                        ddto.setImporteTotal(d.getImporteTotal());
                        ddto.setItem(d.getItem());
                        return ddto;
                    }).toList());
                }
    
                return dto;
            })
            .toList();
    }
    

    // Obtener factura completa por ID
    @Transactional(readOnly = true)
    public InvoiceFullDTO listInvoiceById(Long id) {
        Invoice inv = invoiceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        InvoiceFullDTO dto = new InvoiceFullDTO();
        dto.setId(inv.getId());
        dto.setTipoComprobante(inv.getTipoComprobante());
        dto.setSerie(inv.getSerie());
        dto.setNumero(inv.getNumero());
        dto.setMoneda(inv.getMoneda());
        dto.setTipoOperacion(inv.getTipoOperacion());
        dto.setFechaEmision(inv.getFechaEmision().toString());
        dto.setHoraEmision(inv.getHoraEmision().toString());

        // Cliente
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(inv.getCliente().getId());
        clientDTO.setTipoDocumento(inv.getCliente().getTipoDocumento());
        clientDTO.setNumeroDocumento(inv.getCliente().getNumeroDocumento());
        clientDTO.setRazonSocial(inv.getCliente().getRazonSocial());

        // Dirección embebida
        if (inv.getCliente().getDireccion() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setDireccionCompleta(inv.getCliente().getDireccion().getDireccionCompleta());
            clientDTO.setDireccion(addressDTO);
        }
        dto.setClient(clientDTO);

        // Emisor
        IssuerDTO issuerDTO = new IssuerDTO();
        issuerDTO.setId(inv.getEmisor().getId());
        issuerDTO.setRuc(inv.getEmisor().getRuc());
        issuerDTO.setRazonSocial(inv.getEmisor().getRazonSocial());
        issuerDTO.setNombreComercial(inv.getEmisor().getNombreComercial());
        issuerDTO.setDireccionCompleta(inv.getEmisor().getDireccionCompleta());
        dto.setIssuer(issuerDTO);

        // Forma de pago
        if (inv.getFormaPago() != null) {
            PaymentMethodDTO pmDTO = new PaymentMethodDTO();
            pmDTO.setId(inv.getFormaPago().getId());
            pmDTO.setTipo(inv.getFormaPago().getTipo());
            dto.setPaymentMethod(pmDTO);
        }

        // Totales
        if (inv.getTotales() != null) {
            TotalesDTO totalesDTO = new TotalesDTO();
            totalesDTO.setOpGravada(inv.getTotales().getOpGravada());
            totalesDTO.setOpExonerada(inv.getTotales().getOpExonerada());
            totalesDTO.setOpInafecta(inv.getTotales().getOpInafecta());
            totalesDTO.setOpGratuita(inv.getTotales().getOpGratuita());
            totalesDTO.setIgv(inv.getTotales().getIgv());
            totalesDTO.setTotalImpuestos(inv.getTotales().getTotalImpuestos());
            totalesDTO.setImporteTotal(inv.getTotales().getImporteTotal());
            dto.setTotales(totalesDTO);
        }

        // Items
        if (inv.getItems() != null) {
            dto.setItems(inv.getItems().stream().map(d -> {
                InvoiceDetailDTO ddto = new InvoiceDetailDTO();
                ddto.setId(d.getId());
                ddto.setProductoId(d.getProducto().getId());
                ddto.setDescripcion(d.getDescripcion());
                ddto.setCantidad(d.getCantidad());
                ddto.setPrecioUnitario(d.getPrecioUnitario());
                ddto.setValorUnitario(d.getValorUnitario());
                ddto.setValorVenta(d.getValorVenta());
                ddto.setAfectacionIgv(d.getAfectacionIgv());
                return ddto;
            }).toList());
        }

        return dto;
    }
}
