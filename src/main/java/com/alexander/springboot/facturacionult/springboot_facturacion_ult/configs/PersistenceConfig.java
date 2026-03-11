package com.alexander.springboot.facturacionult.springboot_facturacion_ult.configs;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants.DataSourceRegistry;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants.TenantRoutingDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Bean
    public DataSource dataSource(DataSourceRegistry registry) {
        // Datos central de INTEGRACION
        DataSource defaultDs = DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/INTEGRACION")
                .username("postgres")
                .password("admin")
                .driverClassName("org.postgresql.Driver")
                .build();

        // Fuente de datos de enrutamiento
        return new TenantRoutingDataSource(defaultDs, registry);
    }
}
