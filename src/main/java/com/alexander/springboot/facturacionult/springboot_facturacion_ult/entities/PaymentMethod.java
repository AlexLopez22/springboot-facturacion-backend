package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "formas_pago")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; 

    @OneToMany(mappedBy = "formaPago")
    private List<Invoice> comprobante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Invoice> getComprobante() {
        return comprobante;
    }

    public void setComprobante(List<Invoice> invoices) {
        this.comprobante = invoices;
    }

    
}
