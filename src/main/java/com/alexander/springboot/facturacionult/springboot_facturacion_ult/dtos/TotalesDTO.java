package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import java.math.BigDecimal;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Totales;

public class TotalesDTO {
    private Long id;
    private BigDecimal opGravada;     // Operaciones gravadas
    private BigDecimal opExonerada;   // Operaciones exoneradas
    private BigDecimal opInafecta;    // Operaciones inafectas
    private BigDecimal opGratuita;    // Operaciones gratuitas
    private BigDecimal igv;           // Total IGV
    private BigDecimal totalImpuestos;// Total impuestos
    private BigDecimal importeTotal;  // Importe total de la factura
    
    public TotalesDTO() {
        
    }

    public TotalesDTO(Totales totales) {
        this.id = totales.getId();
        this.opGravada = totales.getOpGravada();
        this.opExonerada = totales.getOpExonerada();
        this.opInafecta = totales.getOpInafecta();
        this.opGratuita = totales.getOpGratuita();
        this.igv = totales.getIgv();
        this.totalImpuestos = totales.getTotalImpuestos();
        this.importeTotal = totales.getImporteTotal();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getOpGravada() {
        return opGravada;
    }
    public void setOpGravada(BigDecimal opGravada) {
        this.opGravada = opGravada;
    }
    public BigDecimal getOpExonerada() {
        return opExonerada;
    }
    public void setOpExonerada(BigDecimal opExonerada) {
        this.opExonerada = opExonerada;
    }
    public BigDecimal getOpInafecta() {
        return opInafecta;
    }
    public void setOpInafecta(BigDecimal opInafecta) {
        this.opInafecta = opInafecta;
    }
    public BigDecimal getOpGratuita() {
        return opGratuita;
    }
    public void setOpGratuita(BigDecimal opGratuita) {
        this.opGratuita = opGratuita;
    }
    public BigDecimal getIgv() {
        return igv;
    }
    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }
    public BigDecimal getTotalImpuestos() {
        return totalImpuestos;
    }
    public void setTotalImpuestos(BigDecimal totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }
    public BigDecimal getImporteTotal() {
        return importeTotal;
    }
    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    
}
