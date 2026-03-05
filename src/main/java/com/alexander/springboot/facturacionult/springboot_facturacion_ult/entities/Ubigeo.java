package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ubigeo" ,schema = "dbo")
public class Ubigeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 6, unique = true, nullable = false)
    private String codigo;

    @Column(length = 50)
    private String departamento;

    @Column(length = 50)
    private String provincia;

    @Column(length = 50)
    private String distrito;

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    
}
