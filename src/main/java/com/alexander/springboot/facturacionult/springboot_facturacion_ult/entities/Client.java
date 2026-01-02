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
    
        // Relación con facturas
        @OneToMany(mappedBy = "cliente")
        private List<Invoice> invoices;

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
