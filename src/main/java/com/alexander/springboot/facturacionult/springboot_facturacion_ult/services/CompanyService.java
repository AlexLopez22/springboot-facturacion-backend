package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Company;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.CompanyRepository;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants.DataSourceRegistry;

import jakarta.annotation.PostConstruct;

@Service
public class CompanyService {

    private final CompanyRepository empresaRepository;
    private final DataSourceRegistry registry;

    // Variables de entorno (Railway)
    private final String host = System.getenv("PGHOST");
    private final String port = System.getenv("PGPORT");
    private final String username = System.getenv("PGUSER");
    private final String password = System.getenv("PGPASSWORD");

    public CompanyService(CompanyRepository empresaRepository, DataSourceRegistry registry) {
        this.empresaRepository = empresaRepository;
        this.registry = registry;
    }

    @PostConstruct
    public void init() {

        List<Company> empresas = empresaRepository.findAll();

        for (Company empresa : empresas) {

            String dbName = empresa.getRuc();

            String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

            DataSource tenantDs = DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url(url)
                    .username(username)
                    .password(password)
                    .build();

            registry.addDataSource(empresa.getRuc(), tenantDs);
        }
    }
}