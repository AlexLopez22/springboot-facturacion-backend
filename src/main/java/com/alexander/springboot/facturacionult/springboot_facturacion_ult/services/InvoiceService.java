package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.*;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.*;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.*;

import java.util.Date;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final IssuerRepository issuerRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final SerieRepository serieRepository;
    private final DocumentRepository documentRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository,
            IssuerRepository issuerRepository, PaymentMethodRepository paymentMethodRepository,
            SerieRepository serieRepository, DocumentRepository documentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.issuerRepository = issuerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.serieRepository = serieRepository;
        this.documentRepository = documentRepository;
    }

@Transactional(readOnly = true)
public List<InvoiceListDTO> listInvoicesTable(
        String fechaInicio,
        String fechaFin,
        String tipoDocumento,
        String serie,
        String correlativo,
        Long clienteId) {

    return invoiceRepository.findAll().stream()
            .filter(inv -> {
                boolean fechaOk = true;
                if (fechaInicio != null && fechaFin != null && inv.getFechaEmision() != null) {
                    LocalDate inicio = LocalDate.parse(fechaInicio);
                    LocalDate fin = LocalDate.parse(fechaFin);

                    LocalDate fechaInv = inv.getFechaEmision(); 
                    fechaOk = !fechaInv.isBefore(inicio) && !fechaInv.isAfter(fin);
                }

                boolean tipoOk = tipoDocumento == null ||
                        (inv.getTipoDocumento() != null
                                && tipoDocumento.equals(inv.getTipoDocumento().getNombre()));

                boolean serieOk = serie == null ||
                        (inv.getSerie() != null && serie.equalsIgnoreCase(inv.getSerie().getNombreSerie()));

                boolean correlativoOk = correlativo == null || correlativo.equals(inv.getNumero());

                boolean clienteOk = clienteId == null ||
                        (inv.getCliente() != null && clienteId.equals(inv.getCliente().getId()));

                return fechaOk && tipoOk && serieOk && correlativoOk && clienteOk;
            })
            .map(inv -> {
                String numeroDoc = inv.getCliente() != null ? inv.getCliente().getNumeroDocumento() : "";
                String nombreCliente = inv.getCliente() != null ? inv.getCliente().getRazonSocial() : "";
                BigDecimal igv = inv.getTotales() != null ? inv.getTotales().getIgv() : BigDecimal.ZERO;
                BigDecimal total = inv.getTotales() != null ? inv.getTotales().getImporteTotal() : BigDecimal.ZERO;
                String estado = inv.getSunat() != null ? inv.getSunat().getEstado() : "PENDIENTE";
                byte[] pdf = inv.getSunat() != null ? inv.getSunat().getCdr() : null;
                return new InvoiceListDTO(
                        inv.getId(),
                        inv.getFechaEmision() != null ? inv.getFechaEmision().toString() : "",
                        inv.getTipoDocumento() != null ? inv.getTipoDocumento().getNombre() : "",
                        inv.getSerie() != null ? inv.getSerie().getNombreSerie() : "",
                        inv.getNumero(),
                        numeroDoc, nombreCliente, igv, total, estado, pdf);
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
        dto.setTipoDocumento(inv.getTipoDocumento() != null ? inv.getTipoDocumento().getNombre() : null);
        dto.setSerie(inv.getSerie() != null ? inv.getSerie().getNombreSerie() : null);
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

        // 1. Crear comprobante PRIMERO
        Invoice invoice = new Invoice();

        Document documento = documentRepository.findById(dto.getTipoDocumento())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de documento no encontrado"));
        invoice.setTipoDocumento(documento);

        Serie serie = serieRepository.findById(dto.getSerie())
                .orElseThrow(() -> new RuntimeException("La serie '" + dto.getSerie() + "' no existe"));
        invoice.setSerie(serie);

        invoice.setNumero(dto.getNumero());
        invoice.setMoneda(dto.getMoneda());
        invoice.setTipoOperacion(dto.getTipoOperacion());

        invoice.setFechaEmision(LocalDate.parse(dto.getFechaEmision()));
        invoice.setHoraEmision(LocalTime.parse(dto.getHoraEmision()));

        // 2. Crear SUNAT (ahora sí correctamente)
        Sunat sunat = new Sunat();
        sunat.setEstado("PENDIENTE");
        sunat.setHashCpe(null);
        sunat.setCdr(null);
        sunat.setFechaEnvio(null);

        // 🔥 RELACIÓN CORRECTA
        sunat.setInvoice(invoice);
        invoice.setSunat(sunat);

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

        // 6. Cuotas
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

        // 7. Guardar
        Invoice saved = invoiceRepository.save(invoice);

        return listInvoiceById(saved.getId());
    }

    public int obtenerSiguienteNumero(String nombreSerie) {
        return invoiceRepository
                .findTopBySerie_NombreSerieOrderByNumeroDesc(nombreSerie)
                .map(inv -> Integer.parseInt(inv.getNumero()) + 1)
                .orElse(1);
    }
    
    public ClientDTO consultarDatosReniec(String dni) {

        String url = "https://api.apis.net.pe/v1/dni?numero=" + dni;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error consultando RENIEC");
        }

        JSONObject json = new JSONObject(response.getBody());

        String nombres = json.optString("nombres");
        String apePat = json.optString("apellidoPaterno");
        String apeMat = json.optString("apellidoMaterno");

        String nombreCompleto = (nombres + " " + apePat + " " + apeMat).trim();

        ClientDTO dto = new ClientDTO();
        dto.setTipoDocumento("1");
        dto.setNumeroDocumento(dni);
        dto.setRazonSocial(nombreCompleto);

        return dto;
    }

    public ClientDTO consultarDatosSunat(String ruc) {

        String url = "https://api.apis.net.pe/v1/ruc?numero=" + ruc;
        String token = "TU_TOKEN_REAL";

        RestTemplate restTemplate = new RestTemplate();

       // HttpHeaders headers = new HttpHeaders();
       // headers.set("Authorization", "Bearer " + token);

     //   HttpEntity<String> entity = new HttpEntity<>(headers);

       // ResponseEntity<String> response = restTemplate.exchange(
         //       url,
           //     HttpMethod.GET,
             //   null,
               // String.class);

       // if (response.getStatusCode() != HttpStatus.OK) {
         //   throw new RuntimeException("Error consultando SUNAT");
       // }

        //JSONObject json = new JSONObject(response.getBody());

        ClientDTO dto = new ClientDTO();
        dto.setTipoDocumento("6");
        return dto;
    }

    public ClientDTO guardar(ClientDTO dto) {

        Client client = new Client();

        client.setTipoDocumento(dto.getTipoDocumento());
        client.setNumeroDocumento(dto.getNumeroDocumento());
        client.setRazonSocial(dto.getRazonSocial());

        // 📍 Dirección
        if (dto.getDireccion() != null) {
            Address address = new Address();
            address.setDireccionCompleta(dto.getDireccion().getDireccionCompleta());
            client.setDireccion(address);
        }

        // 📍 Campos SUNAT opcionales
        client.setEstado(dto.getEstado());
        client.setCondicion(dto.getCondicion());
        client.setUbigeo(dto.getUbigeo());
        client.setViaTipo(dto.getViaTipo());
        client.setViaNombre(dto.getViaNombre());
        client.setZonaTipo(dto.getZonaTipo());

        Client saved = clientRepository.save(client);

        return new ClientDTO(saved);
    }
}

