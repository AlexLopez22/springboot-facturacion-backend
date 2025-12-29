package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "condicion_pago", nullable = false)
    private String condicionPago; 

    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @Column(name = "moneda", nullable = false)
    private String moneda;

    @Column(name = "tipo_operacion", nullable = false)
    private String tipoOperacion;

    @Column(name = "serie", nullable = false)
    private String serie;

    @Column(name = "correlativo", nullable = false)
    private Integer correlativo;

    @Column(name = "sub_total", nullable = false)
    private Double subtotal;

    @Column(name = "igv_total", nullable = false)
    private Double igvTotal;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "tipo_cambio")
    private Double tipoCambio;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
 
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDetail> detalles;
    

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Installment> cuotas;

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

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime updatedAt) {
        this.fechaActualizacion = updatedAt;
    }

    public List<InvoiceDetail> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<InvoiceDetail> detalles) {
        this.detalles = detalles;
    }

    public List<Installment> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Installment> cuotas) {
        this.cuotas = cuotas;
    }

}
