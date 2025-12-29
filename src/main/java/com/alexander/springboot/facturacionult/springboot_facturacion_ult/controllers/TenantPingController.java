package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;


import com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants.TenantContext;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
Punto protegido para verificar que la base de datos del tenant sea accesible
 */
@RestController
public class TenantPingController {

    private final JdbcTemplate jdbcTemplate;

    public TenantPingController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/api/tenant/ping")
    public ResponseEntity<String> ping() {
        Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        String db = TenantContext.getTenant();
        return ResponseEntity.ok("Tenant " + db + " OK (" + one + ")");
    }
}
