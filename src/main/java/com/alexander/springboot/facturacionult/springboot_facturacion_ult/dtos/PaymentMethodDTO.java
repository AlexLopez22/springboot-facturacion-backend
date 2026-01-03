package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.PaymentMethod;

public class PaymentMethodDTO {
    private Long id;
    private String tipo; // Contado, Crédito, etc.

    public PaymentMethodDTO(PaymentMethod pm) {
        this.id = pm.getId();
        this.tipo = pm.getTipo();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
 
}
