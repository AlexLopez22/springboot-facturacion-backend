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
    private ClientDTO cliente;
    private IssuerDTO emisor;
    private PaymentMethodDTO formaPago;
    private List<InvoiceDetailDTO> items;
    private TotalesDTO totales;
    private List<InstallmentDTO> cuotas;

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
    public ClientDTO getCliente() {
        return cliente;
    }
    public void setCliente(ClientDTO client) {
        this.cliente = client;
    }
    public IssuerDTO getEmisor() {
        return emisor;
    }
    public void setEmisor(IssuerDTO issuer) {
        this.emisor = issuer;
    }
    public PaymentMethodDTO getFormaPago() {
        return formaPago;
    }
    public void setFormaPago(PaymentMethodDTO paymentMethod) {
        this.formaPago = paymentMethod;
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
    public List<InstallmentDTO> getCuotas() {
        return cuotas;
    }
    public void setCuotas(List<InstallmentDTO> cuotas) {
        this.cuotas = cuotas;
    }

        
}
