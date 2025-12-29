package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cuotas")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Invoice factura;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "monto", nullable = false)
    private Double monto;

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
