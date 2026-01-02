package com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos;

public class SunatDTO {
    private Long id;
    private String hashCpe;
    private String estado;
    private byte[] cdr;
    private String fechaEnvio;
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
    public String getFechaEnvio() {
        return fechaEnvio;
    }
    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    
  }
