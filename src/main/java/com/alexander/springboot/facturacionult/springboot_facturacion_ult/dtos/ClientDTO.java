package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;



public class ClientDTO {
    private String ruc;
    private String dni;
    private String nombreRazonSocial;
    private String direccion;
    private String celular;
    public String getRuc() {
        return ruc;
    }
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }
    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
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
