package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @OneToMany(mappedBy = "formaPago", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Invoice> comprobantes;

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

    public List<Invoice> getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(List<Invoice> comprobantes) {
        this.comprobantes = comprobantes;
    }

}
