package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Installment;

public class InstallmentDTO {
    private Integer numeroCuota;
    private String fechaVencimiento;
    private Double importe;

    public InstallmentDTO() {}

    public InstallmentDTO(Installment installment) {
        this.numeroCuota = installment.getNumeroCuota();
        this.fechaVencimiento = installment.getFechaVencimiento().toString();
        this.importe = installment.getImporte();
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    
}
