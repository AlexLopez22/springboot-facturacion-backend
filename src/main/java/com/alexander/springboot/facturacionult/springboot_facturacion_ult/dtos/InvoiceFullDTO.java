package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceFullDTO {
    private Long id;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaRegistro;
    private Long clienteId;
    private String condicionPago;
    private String tipoDocumento;
    private String moneda;
    private String tipoOperacion;
    private String serie;
    private Integer correlativo;
    private Double subtotal;
    private Double igvTotal;
    private Double total;
    private Double tipoCambio;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualiacion;

    private List<InvoiceDetailDTO> detalles;
    private List<InstallmentDTO> cuotas;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(LocalDateTime issueDate) {
        this.fechaEmision = issueDate;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDateTime registerDate) {
        this.fechaRegistro = registerDate;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clientId) {
        this.clienteId = clientId;
    }
    public String getCondicionPago() {
        return condicionPago;
    }
    public void setCondicionPago(String paymentCondition) {
        this.condicionPago = paymentCondition;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String documentType) {
        this.tipoDocumento = documentType;
    }
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String currency) {
        this.moneda = currency;
    }
    public String getTipoOperacion() {
        return tipoOperacion;
    }
    public void setTipoOperacion(String operationType) {
        this.tipoOperacion = operationType;
    }
    public String getSerie() {
        return serie;
    }
    public void setSerie(String series) {
        this.serie = series;
    }
    public Integer getCorrelativo() {
        return correlativo;
    }
    public void setCorrelativo(Integer correlative) {
        this.correlativo = correlative;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    public Double getIgvTotal() {
        return igvTotal;
    }
    public void setIgvTotal(Double igvTotal) {
        this.igvTotal = igvTotal;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Double getTipoCambio() {
        return tipoCambio;
    }
    public void setTipoCambio(Double exchangeRate) {
        this.tipoCambio = exchangeRate;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String status) {
        this.estado = status;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime createdAt) {
        this.fechaCreacion = createdAt;
    }
    public LocalDateTime getFechaActualiacion() {
        return fechaActualiacion;
    }
    public void setFechaActualiacion(LocalDateTime updatedAt) {
        this.fechaActualiacion = updatedAt;
    }
    public List<InvoiceDetailDTO> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<InvoiceDetailDTO> details) {
        this.detalles = details;
    }
    public List<InstallmentDTO> getCuotas() {
        return cuotas;
    }
    public void setCuotas(List<InstallmentDTO> installments) {
        this.cuotas = installments;
    }

}
