package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sunat")
public class Sunat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hash_cpe")
    private String hashCpe;
    
    private String estado;

    @Lob
    private byte[] cdr;
        
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @OneToOne(mappedBy = "sunat")
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashCpe() {
        return hashCpe;
    }

    public void setHashCpe(String hashCpe) {
        this.hashCpe = hashCpe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getCdr() {
        return cdr;
    }

    public void setCdr(byte[] cdr) {
        this.cdr = cdr;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    
}
