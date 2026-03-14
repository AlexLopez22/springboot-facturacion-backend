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

        String databaseUrl = System.getenv("DATABASE_URL");

        if (databaseUrl == null) {
            throw new RuntimeException("DATABASE_URL no encontrada en Railway");
        }

        databaseUrl = databaseUrl.replace("postgres://", "jdbc:postgresql://");

        DataSource defaultDs = DataSourceBuilder.create()
                .url(databaseUrl)
                .driverClassName("org.postgresql.Driver")
                .build();

        return new TenantRoutingDataSource(defaultDs, registry);
    }
}