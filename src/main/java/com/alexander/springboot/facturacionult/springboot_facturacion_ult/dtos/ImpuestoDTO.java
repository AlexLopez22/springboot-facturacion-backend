package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import java.math.BigDecimal;

public class ImpuestoDTO {
    private Long id;
    private String codigoTributo;      // Ej: "1000"
    private String nombreTributo;      // Ej: "IGV"
    private String codigoTipoTributo;  // Ej: "VAT"
    private BigDecimal baseImponible;  // Base sobre la que se calcula
    private BigDecimal montoImpuesto;  // Monto del impuesto
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



}

