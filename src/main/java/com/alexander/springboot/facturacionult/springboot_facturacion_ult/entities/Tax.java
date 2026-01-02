package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "impuestos")
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_impuestos")
    private BigDecimal totalImpuestos;

    @ManyToOne
    @JoinColumn(name = "comprobante_id", nullable = false)
    private Invoice invoice;

    @OneToMany(mappedBy = "tax", cascade = CascadeType.ALL)
    private List<TaxDetail> details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(BigDecimal totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<TaxDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TaxDetail> details) {
        this.details = details;
    }

    
}
