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

        String host = System.getenv("PGHOST");
        String port = System.getenv("PGPORT");
        String db = System.getenv("PGDATABASE");
        String user = System.getenv("PGUSER");
        String password = System.getenv("PGPASSWORD");

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;

        DataSource defaultDs = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(url)
                .username(user)
                .password(password)
                .build();

        return new TenantRoutingDataSource(defaultDs, registry);
    }
}