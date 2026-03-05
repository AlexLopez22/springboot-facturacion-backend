package com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities;

import java.util.List;


import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Client {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        @Column(name = "tipo_documento", nullable = false)
        private String tipoDocumento;
    
        @Column(name = "numero_documento", nullable = false)
        private String numeroDocumento;
    
        @Column(name = "razon_social", nullable = false)
        private String razonSocial;
    
        @Embedded 
        @AttributeOverrides({ @AttributeOverride(name = "direccionCompleta", column = @Column(name = "direccion_completa")) }) 
        private Address direccion; 

        @Column(name = "estado")
        private String estado;

        @Column(name = "condicion")
        private String condicion;

        @Column(name = "ubigeo")
        private String ubigeo;

        @Column(name = "via_tipo")
        private String viaTipo;

        @Column(name = "via_nombre")
        private String viaNombre;

        @Column(name = "zona_tipo")
        private String zonaTipo;

    
        // Relación con facturas
        @OneToMany(mappedBy = "cliente")
        private List<Invoice> invoices;

        
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

        public Address getDireccion() {
            return direccion;
        }

        public void setDireccion(Address direccion) {
            this.direccion = direccion;
        }

        public List<Invoice> getInvoices() {
            return invoices;
        }

        public void setInvoices(List<Invoice> invoices) {
            this.invoices = invoices;
        }

   

}
