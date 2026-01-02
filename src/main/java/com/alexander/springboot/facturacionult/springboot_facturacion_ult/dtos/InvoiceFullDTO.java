package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import java.util.List;

public class InvoiceFullDTO {
    private Long id;
    private String tipoComprobante;
    private String serie;
    private String numero;
    private String moneda;
    private String tipoOperacion;
    private String fechaEmision;
    private String horaEmision;

    private ClientDTO client;
    private IssuerDTO issuer;
    private PaymentMethodDTO paymentMethod;

    private List<InvoiceDetailDTO> items;
    private TotalesDTO totales;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipoComprobante() {
        return tipoComprobante;
    }
    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
    public String getSerie() {
        return serie;
    }
    public void setSerie(String serie) {
        this.serie = serie;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public String getTipoOperacion() {
        return tipoOperacion;
    }
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
    public String getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    public String getHoraEmision() {
        return horaEmision;
    }
    public void setHoraEmision(String horaEmision) {
        this.horaEmision = horaEmision;
    }
    public ClientDTO getClient() {
        return client;
    }
    public void setClient(ClientDTO client) {
        this.client = client;
    }
    public IssuerDTO getIssuer() {
        return issuer;
    }
    public void setIssuer(IssuerDTO issuer) {
        this.issuer = issuer;
    }
    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public List<InvoiceDetailDTO> getItems() {
        return items;
    }
    public void setItems(List<InvoiceDetailDTO> items) {
        this.items = items;
    }
    public TotalesDTO getTotales() {
        return totales;
    }
    public void setTotales(TotalesDTO totales) {
        this.totales = totales;
    }

        
}
