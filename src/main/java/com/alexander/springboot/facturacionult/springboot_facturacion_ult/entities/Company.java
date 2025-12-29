package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empresas")
public class Company {

    @Id
    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;   // clave primaria

    @Column(name = "razon_social", nullable = false, length = 255)
    private String razonSocial;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "celular", length = 9)
    private String celular;

    // Constructor vacío requerido por JPA
    public Company() {}

    public Company(String ruc, String razonSocial, String direccion, String celular) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.celular = celular;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

   
}
