package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Address;

public class AddressDTO {
    private String direccionCompleta;

    public AddressDTO(Address address) { 
        this.direccionCompleta = address.getDireccionCompleta(); 
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }
}

  

