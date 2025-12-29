package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_factura")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Invoice factura;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "afecta_igv", nullable = false)
    private Boolean afectaIgv;

    @Column(name = "valor_venta", nullable = false)
    private Double valorVenta;

    @Column(name = "igv", nullable = false)
    private Double igv;

    @Column(name = "total_linea", nullable = false)
    private Double totalLinea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getFactura() {
        return factura;
    }

    public void setFactura(Invoice invoice) {
        this.factura = invoice;
    }

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

    public Boolean getAfectaIgv() {
        return afectaIgv;
    }

    public void setAfectaIgv(Boolean affectsIgv) {
        this.afectaIgv = affectsIgv;
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
