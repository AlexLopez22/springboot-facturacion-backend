package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;


public class InvoiceDTO { 

    private Long id; 
    private Long clienteId; 
    private Double total; 
    private String estado; 
    private String condicionPago;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clientId) {
        this.clienteId = clientId;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String status) {
        this.estado = status;
    }
    public String getCondicionPago() {
        return condicionPago;
    }
    public void setCondicionPago(String paymentCondition) {
        this.condicionPago = paymentCondition;
    }

}