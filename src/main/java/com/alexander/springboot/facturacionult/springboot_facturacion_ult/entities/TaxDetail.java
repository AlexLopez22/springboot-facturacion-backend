package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "impuestos_detalles")
public class TaxDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo_tributo")
    private String codigoTributo;

    @Column(name = "nombreTributo")
    private String nombreTributo;

    @Column(name = "codigo_tipo_tributo")
    private String codigoTipoTributo;

    @Column(name = "base_imponible")
    private BigDecimal baseImponible;

    @Column(name = "monto_impuesto")
    private BigDecimal montoImpuesto;

    @ManyToOne
    @JoinColumn(name = "impuesto_id", nullable = false)
    private Tax tax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoTributo() {
        return codigoTributo;
    }

    public void setCodigoTributo(String codigoTributo) {
        this.codigoTributo = codigoTributo;
    }

    public String getNombreTributo() {
        return nombreTributo;
    }

    public void setNombreTributo(String nombreTributo) {
        this.nombreTributo = nombreTributo;
    }

    public String getCodigoTipoTributo() {
        return codigoTipoTributo;
    }

    public void setCodigoTipoTributo(String codigoTipoTributo) {
        this.codigoTipoTributo = codigoTipoTributo;
    }

    public BigDecimal getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(BigDecimal baseImponible) {
        this.baseImponible = baseImponible;
    }

    public BigDecimal getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(BigDecimal montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    
}
