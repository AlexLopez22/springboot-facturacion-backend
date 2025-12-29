package com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 Cache de DataSources por tenantDb.
 Permite registrar manualmente (addDataSource) o construir dinámicamente (getDataSource).
 */
@Component
public class DataSourceRegistry {

    private final Map<String, DataSource> dataSources = new HashMap<>();

    
    //Registrar manualmente un DataSource para un tenant.
    public void addDataSource(String tenantId, DataSource dataSource) {
        dataSources.put(tenantId, dataSource);
    }

    /**
    Obtener el DataSource para un tenant.
    Si no existe en cache, lo construye dinámicamente y lo guarda.
    */
    public DataSource getDataSource(String tenantId) {
        if (tenantId == null || tenantId.isBlank()) {
            return null;
        }

        // Si ya existe en cache, lo devuelve
        if (dataSources.containsKey(tenantId)) {
            return dataSources.get(tenantId);
        }

        // Construye un nuevo DataSource dinámicamente
        DataSource ds = DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url("jdbc:sqlserver://localhost:1433;databaseName=" + tenantId + ";encrypt=false")
                .username("springboot_user")
                .password("P@$$w0rd")
                .build();

        // Lo guarda en cache
        dataSources.put(tenantId, ds);

        return ds;
    }
}
