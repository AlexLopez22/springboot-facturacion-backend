package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Issuer;

public class IssuerDTO {
    private Long id;
    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String ubigeo;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccionCompleta;
    private String codigoPais;

    public IssuerDTO(Issuer issuer) {
        this.id = issuer.getId();
        this.ruc = issuer.getRuc();
        this.razonSocial = issuer.getRazonSocial();
        this.nombreComercial = issuer.getNombreComercial();
        this.ubigeo = issuer.getUbigeo();
        this.departamento = issuer.getDepartamento();
        this.provincia = issuer.getProvincia();
        this.distrito = issuer.getDistrito();
        this.direccionCompleta = issuer.getDireccionCompleta();
        this.codigoPais = issuer.getCodigoPais();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getNombreComercial() {
        return nombreComercial;
    }
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    public String getUbigeo() {
        return ubigeo;
    }
    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getDistrito() {
        return distrito;
    }
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    public String getDireccionCompleta() {
        return direccionCompleta;
    }
    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }
    public String getCodigoPais() {
        return codigoPais;
    }
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    
}
