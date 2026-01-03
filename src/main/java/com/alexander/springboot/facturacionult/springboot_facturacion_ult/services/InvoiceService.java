package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceDetailDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InvoiceFullDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ClientDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.InstallmentDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.IssuerDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.PaymentMethodDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.TotalesDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Installment;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Invoice;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.InvoiceDetail;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Issuer;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.PaymentMethod;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Product;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Sunat;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Totales;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.ClientRepository;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.InvoiceRepository;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.IssuerRepository;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.PaymentMethodRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final IssuerRepository issuerRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    
    public InvoiceService   (InvoiceRepository invoiceRepository, ClientRepository clientRepository,
                            IssuerRepository issuerRepository, PaymentMethodRepository paymentMethodRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.issuerRepository = issuerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }
    
    // Listar facturas
    public List<InvoiceFullDTO> listInvoices() {
        return invoiceRepository.findAll().stream()
            .map(inv -> {
                InvoiceFullDTO dto = new InvoiceFullDTO();
                dto.setId(inv.getId());
                dto.setTipoComprobante(inv.getTipoComprobante());
                dto.setSerie(inv.getSerie());
                dto.setNumero(inv.getNumero());
                dto.setMoneda(inv.getMoneda());
                dto.setTipoOperacion(inv.getTipoOperacion());
                dto.setFechaEmision(inv.getFechaEmision() != null ? inv.getFechaEmision().toString() : null);
                dto.setHoraEmision(inv.getHoraEmision() != null ? inv.getHoraEmision().toString() : null);
    
                // constructores de los DTOs
                dto.setCliente(new ClientDTO(inv.getCliente()));
                dto.setEmisor(new IssuerDTO(inv.getEmisor()));
    
                if (inv.getFormaPago() != null) {
                    dto.setFormaPago(new PaymentMethodDTO(inv.getFormaPago()));
                }
                if (inv.getTotales() != null) {
                    dto.setTotales(new TotalesDTO(inv.getTotales()));
                }
                if (inv.getItems() != null) {
                    dto.setItems(inv.getItems().stream().map(InvoiceDetailDTO::new).toList());
                }
                if (inv.getCuotas() != null) {
                    dto.setCuotas(inv.getCuotas().stream().map(InstallmentDTO::new).toList());
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
        
            // constructores de los DTOs para simplificar
            dto.setCliente(new ClientDTO(inv.getCliente()));
            dto.setEmisor(new IssuerDTO(inv.getEmisor()));
        
            if (inv.getFormaPago() != null) {
                dto.setFormaPago(new PaymentMethodDTO(inv.getFormaPago()));
            }
            if (inv.getTotales() != null) {
                dto.setTotales(new TotalesDTO(inv.getTotales()));
            }
            if (inv.getItems() != null) {
                dto.setItems(inv.getItems().stream().map(InvoiceDetailDTO::new).toList());
            }
            if (inv.getCuotas() != null) {
                dto.setCuotas(inv.getCuotas().stream().map(InstallmentDTO::new).toList());
            }
            return dto;
        }

    // Crear factura
@Transactional
public InvoiceFullDTO createInvoice(InvoiceDTO dto) {
    // 1. Crear registro en SUNAT con estado pendiente
    Sunat sunat = new Sunat();
    sunat.setEstado("PENDIENTE");
    sunat.setHashCpe(null);
    sunat.setCdr(null);
    sunat.setFechaEnvio(null);

    // 2. Crear comprobante
    Invoice invoice = new Invoice();
    invoice.setTipoComprobante(dto.getTipoComprobante());
    invoice.setSerie(dto.getSerie());
    invoice.setNumero(dto.getNumero());
    invoice.setMoneda(dto.getMoneda());
    invoice.setTipoOperacion(dto.getTipoOperacion());

    invoice.setFechaEmision(LocalDate.parse(dto.getFechaEmision()));
    invoice.setHoraEmision(LocalTime.parse(dto.getHoraEmision()));

    // 3. Relacionar cliente, emisor y forma de pago
    Client client = clientRepository.findById(dto.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    invoice.setCliente(client);

    Issuer issuer = issuerRepository.findById(dto.getEmisorId())
        .orElseThrow(() -> new RuntimeException("Emisor no encontrado"));
    invoice.setEmisor(issuer);

    if (dto.getFormaPagoId() != null) {
        PaymentMethod pm = paymentMethodRepository.findById(dto.getFormaPagoId())
            .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada"));
        invoice.setFormaPago(pm);
    }

    // 4. Totales
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

    // 5. Items
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
            detail.setInvoice(invoice);
            return detail;
        }).toList();
        invoice.setItems(details);
    }

    // 6. Cuotas (solo si forma de pago es crédito)
    if (dto.getCuotas() != null && !dto.getCuotas().isEmpty()) {
        List<Installment> installments = dto.getCuotas().stream().map(c -> {
            Installment inst = new Installment();
            inst.setNumeroCuota(c.getNumeroCuota());
            inst.setFechaVencimiento(LocalDate.parse(c.getFechaVencimiento()));
            inst.setImporte(c.getImporte());
            inst.setInvoice(invoice);
            return inst;
        }).toList();
        invoice.setCuotas(installments); 
    }

    // 7. Asociar comprobante con el registro SUNAT
    invoice.setSunat(sunat);

    // 8. Guardar comprobante (Hibernate insertará primero Sunat y luego Invoice)
    Invoice saved = invoiceRepository.save(invoice);

    return listInvoiceById(saved.getId());
}

}