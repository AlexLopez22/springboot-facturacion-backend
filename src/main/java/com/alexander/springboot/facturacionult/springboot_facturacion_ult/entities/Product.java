package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String codigo;
        private String descripcion;

        @Column(name = "unidad_medida")
        private String unidadMedida;

        @Column(name = "afectacion_igv")
        private String afectacionIgv;

        @Column(name = "estado")
        private String estado;
    
        @OneToMany(mappedBy = "producto")
        private List<InvoiceDetail> items;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getUnidadMedida() {
            return unidadMedida;
        }

        public void setUnidadMedida(String unidadMedida) {
            this.unidadMedida = unidadMedida;
        }

        public String getAfectacionIgv() {
            return afectacionIgv;
        }

        public void setAfectacionIgv(String afectacionIgv) {
            this.afectacionIgv = afectacionIgv;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public List<InvoiceDetail> getItems() {
            return items;
        }

        public void setItems(List<InvoiceDetail> items) {
            this.items = items;
        }
    

    
}
