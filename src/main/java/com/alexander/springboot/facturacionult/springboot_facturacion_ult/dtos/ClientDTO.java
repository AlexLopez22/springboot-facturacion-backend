package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;



public class ClientDTO {
    private Long id; 
    private String tipoDocumento; 
    private String numeroDocumento; 
    private String razonSocial; 
    private AddressDTO  direccion;
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
