package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import java.math.BigDecimal;

public class InvoiceListDTO {

    private Long id;
    private String fecha;
    private String documento;
    private String serie;
    private String correlativo;
    private String numeroDocumentoCliente;
    private String clienteNombre;
    private BigDecimal igv;
    private BigDecimal total;
    private String estado;
    private byte[]  pdf;

 
    
    public InvoiceListDTO(Long id, String fecha, String documento, String serie, String correlativo,
            String numeroDocumentoCliente, String clienteNombre, BigDecimal igv, BigDecimal total, String estado,
            byte[] pdf) {
        this.id = id;
        this.fecha = fecha;
        this.documento = documento;
        this.serie = serie;
        this.correlativo = correlativo;
        this.numeroDocumentoCliente = numeroDocumentoCliente;
        this.clienteNombre = clienteNombre;
        this.igv = igv;
        this.total = total;
        this.estado = estado;
        this.pdf = pdf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getNumeroDocumentoCliente() {
        return numeroDocumentoCliente;
    }

    public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
        this.numeroDocumentoCliente = numeroDocumentoCliente;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }


}