package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Company;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.CompanyRepository;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants.DataSourceRegistry;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;

@Service
public class CompanyService {

    private final CompanyRepository empresaRepository;
    private final DataSourceRegistry registry;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public CompanyService(CompanyRepository empresaRepository, DataSourceRegistry registry) {
        this.empresaRepository = empresaRepository;
        this.registry = registry;
    }

    @PostConstruct
    public void init() {

        List<Company> empresas = empresaRepository.findAll();

        for (Company empresa : empresas) {

            String dbName = empresa.getRuc();
            String url = databaseUrl.replaceFirst("/[^/]*$", "/" + dbName);

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