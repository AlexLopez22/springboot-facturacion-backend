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

    public CompanyService(CompanyRepository empresaRepository, DataSourceRegistry registry) {
        this.empresaRepository = empresaRepository;
        this.registry = registry;
    }

    @PostConstruct
    public void init() {

        List<Company> empresas = empresaRepository.findAll();

        for (Company empresa : empresas) {

            String dbName = empresa.getRuc();

            DataSource tenantDs = DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url("jdbc:postgresql://host:5432/" + dbName)
                    .username("postgres")
                    .password("admin")
                    .build();

            registry.addDataSource(empresa.getRuc(), tenantDs);
        }
    }
}