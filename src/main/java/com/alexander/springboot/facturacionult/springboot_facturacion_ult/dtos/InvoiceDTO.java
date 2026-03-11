package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import java.util.List;

public class InvoiceDTO { 
    private Long id;
    private Integer tipoDocumento; 
    private Integer serie; 
    private String numero;
    private String moneda;
    private String tipoOperacion;
    private Long clienteId;
    private Long emisorId;
    private Long formaPagoId;
    private String fechaEmision;
    private String horaEmision; 
    private List<InvoiceDetailDTO> items;
    private TotalesDTO totales;
    private List<InstallmentDTO> cuotas;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public Integer getSerie() {
        return serie;
    }
    public void setSerie(Integer serie) {
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
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    public Long getEmisorId() {
        return emisorId;
    }
    public void setEmisorId(Long emisorId) {
        this.emisorId = emisorId;
    }
    public Long getFormaPagoId() {
        return formaPagoId;
    }
    public void setFormaPagoId(Long formaPagoId) {
        this.formaPagoId = formaPagoId;
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