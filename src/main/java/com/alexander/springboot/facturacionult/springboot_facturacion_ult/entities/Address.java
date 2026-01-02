package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    
    private String direccionCompleta; // Ej: "JR. CLIENTE 456"

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }


}
