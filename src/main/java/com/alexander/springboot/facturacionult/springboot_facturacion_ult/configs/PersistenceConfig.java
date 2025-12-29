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
        // Datos central  de TIS_INTEGRADO
        DataSource defaultDs = DataSourceBuilder.create()
                .url("jdbc:sqlserver://localhost:1433;databaseName=TIS_INTEGRADO;encrypt=false")
                .username("springboot_user")
                .password("P@$$w0rd")
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .build();

        //Fuente de datos de enrutamiento 
        return new TenantRoutingDataSource(defaultDs, registry);
    }
}
