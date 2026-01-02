package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "totales")
public class Totales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "op_gravada")
    private BigDecimal opGravada;

    @Column(name = "op_exonerada")
    private BigDecimal opExonerada;

    @Column(name = "op_inafecta")
    private BigDecimal opInafecta;

    @Column(name = "op_gratuita")
    private BigDecimal opGratuita;

    @Column(name = "igv")
    private BigDecimal igv;

    @Column(name = "total_impuestos")
    private BigDecimal totalImpuestos;

    @Column(name = "importe_total")
    private BigDecimal importeTotal;

    @OneToOne(mappedBy = "totales")
    private Invoice invoice;

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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    
}
