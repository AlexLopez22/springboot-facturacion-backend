package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "comprobantes")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_comprobante")
    private String tipoComprobante;

    @Column(name = "serie")
    private String serie;

    @Column(name = "numero")
    private String numero;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "hora_emision")
    private LocalTime horaEmision;
    
    @Column(name = "moneda")
    private String moneda;

    @Column(name = "tipo_operacion")
    private String tipoOperacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Client cliente;

    @ManyToOne
    @JoinColumn(name = "emisor_id", nullable = false)
    private Issuer emisor;

    @ManyToOne
    @JoinColumn(name = "forma_pago_id")
    private PaymentMethod formaPago;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "totales_id")
    private Totales totales;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Tax> impuestos;
 

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Legend> leyendas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sunat_id")
    private Sunat sunat;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Installment> cuotas;;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalTime getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(LocalTime horaEmision) {
        this.horaEmision = horaEmision;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Issuer getEmisor() {
        return emisor;
    }

    public void setEmisor(Issuer emisor) {
        this.emisor = emisor;
    }

    public PaymentMethod getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(PaymentMethod formaPago) {
        this.formaPago = formaPago;
    }

    public List<InvoiceDetail> getItems() {
        return items;
    }

    public void setItems(List<InvoiceDetail> items) {
        this.items = items;
    }

    public Totales getTotales() {
        return totales;
    }

    public void setTotales(Totales totales) {
        this.totales = totales;
    }

    public List<Tax> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<Tax> impuestos) {
        this.impuestos = impuestos;
    }

    public List<Legend> getLeyendas() {
        return leyendas;
    }

    public void setLeyendas(List<Legend> leyendas) {
        this.leyendas = leyendas;
    }

    public Sunat getSunat() {
        return sunat;
    }

    public void setSunat(Sunat sunat) {
        this.sunat = sunat;
    }

    public List<Installment> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Installment> cuotas) {
        this.cuotas = cuotas;
    }



}
