package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Client;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO {
    private Long id; 
    private String tipoDocumento; 
    private String numeroDocumento; 
    private String razonSocial; 
    private AddressDTO  direccion;
    private String estado;
    private String condicion;
    private String ubigeo;
    private String viaTipo;
    private String viaNombre;
    private String zonaTipo;
    
    @JsonProperty("nombre")
    private String nombre;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.tipoDocumento = client.getTipoDocumento();
        this.numeroDocumento = client.getNumeroDocumento();
        this.razonSocial = client.getRazonSocial();
        if (client.getDireccion() != null) { 
            this.direccion = new AddressDTO(client.getDireccion());
            
        }
        this.estado = client.getEstado();
        this.condicion = client.getCondicion();
        this.ubigeo = client.getUbigeo();
        this.viaTipo = client.getViaTipo();
        this.viaNombre = client.getViaNombre();
        this.zonaTipo = client.getZonaTipo();
    }
    
    public ClientDTO() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getViaTipo() {
        return viaTipo;
    }

    public void setViaTipo(String viaTipo) {
        this.viaTipo = viaTipo;
    }

    public String getViaNombre() {
        return viaNombre;
    }

    public void setViaNombre(String viaNombre) {
        this.viaNombre = viaNombre;
    }

    public String getZonaTipo() {
        return zonaTipo;
    }

    public void setZonaTipo(String zonaTipo) {
        this.zonaTipo = zonaTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public AddressDTO getDireccion() {
        return direccion;
    }
    public void setDireccion(AddressDTO direccion) {
        this.direccion = direccion;
    }
    
   
    
}
