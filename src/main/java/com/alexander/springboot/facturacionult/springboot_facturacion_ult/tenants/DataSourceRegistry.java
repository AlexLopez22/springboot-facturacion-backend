package com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceRegistry {

    private final Map<String, DataSource> dataSources = new HashMap<>();

    // Railway proporciona esta variable automáticamente
    private final String databaseUrl = System.getenv("DATABASE_URL");

    public void addDataSource(String tenantId, DataSource dataSource) {
        dataSources.put(tenantId, dataSource);
    }

    public DataSource getDataSource(String tenantId) {

        if (tenantId == null || tenantId.isBlank()) {
            return null;
        }

        if (dataSources.containsKey(tenantId)) {
            return dataSources.get(tenantId);
        }

        // Cambia solo el nombre de la base de datos por el RUC
        String url = databaseUrl.replaceFirst("/[^/]*$", "/" + tenantId);

        DataSource ds = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(url)
                .build();

        dataSources.put(tenantId, ds);

        return ds;
    }
}