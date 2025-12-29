package com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * RoutingDataSource que selecciona el DataSource correcto según el tenant.
 */
public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    private final DataSourceRegistry registry;
    private final DataSource defaultDataSource;

    public TenantRoutingDataSource(DataSource defaultDs, DataSourceRegistry registry) {
        this.registry = registry;
        this.defaultDataSource = defaultDs;

        // Configuramos el DataSource por defecto
        setDefaultTargetDataSource(defaultDs);

        // Spring exige un mapa targetDataSources, aunque lo resolvamos dinámicamente
        Map<Object, Object> targets = new HashMap<>();
        targets.put("default", defaultDs);
        setTargetDataSources(targets);

        // Inicializamos la configuración interna
        afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenant();
    }

    @NonNull
    @Override
    protected DataSource determineTargetDataSource() {
        String tenantDb = TenantContext.getTenant();
        if (tenantDb == null || tenantDb.isBlank()) {
            return defaultDataSource;
        }
        DataSource ds = registry.getDataSource(tenantDb);
        return (ds != null ? ds : defaultDataSource);
    }
}
