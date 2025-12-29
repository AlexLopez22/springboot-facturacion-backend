package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

public class InvoiceDetailDTO {
    
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double valorVenta;
    private Double igv;
    private Double totalLinea;

    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productId) {
        this.productoId = productId;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer quantity) {
        this.cantidad = quantity;
    }
    public Double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(Double unitPrice) {
        this.precioUnitario = unitPrice;
    }
    public Double getValorVenta() {
        return valorVenta;
    }
    public void setValorVenta(Double saleValue) {
        this.valorVenta = saleValue;
    }
    public Double getIgv() {
        return igv;
    }
    public void setIgv(Double igv) {
        this.igv = igv;
    }
    public Double getTotalLinea() {
        return totalLinea;
    }
    public void setTotalLinea(Double lineTotal) {
        this.totalLinea = lineTotal;
    }

}
