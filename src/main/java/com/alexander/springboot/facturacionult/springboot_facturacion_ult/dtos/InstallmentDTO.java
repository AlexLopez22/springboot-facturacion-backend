package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import java.time.LocalDate;

public class InstallmentDTO {
    private Integer numero;
    private LocalDate fechaVencimiento;
    private Double monto;

    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer number) {
        this.numero = number;
    }
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(LocalDate dueDate) {
        this.fechaVencimiento = dueDate;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double amount) {
        this.monto = amount;
    }
  

}
