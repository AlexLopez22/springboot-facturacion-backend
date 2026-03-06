package com.alexander.springboot.facturacionult.springboot_facturacion_ult.karate;

import com.intuit.karate.junit5.Karate;

/**
 * Runner principal de Karate.
 * Ejecutar con: mvn test -Dtest=KarateRunner
 * Con entorno:  mvn test -Dtest=KarateRunner -Dkarate.env=staging
 * Solo @smoke:  mvn test -Dtest=KarateRunner -Dkarate.options="--tags @smoke"
 */
class KarateRunner {

    // Corre TODOS los features
    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:karate")
                     .tags("~@ignore")
                     .relativeTo(getClass());
    }

    // Solo smoke tests (rápidos, para el pipeline de CI)
    @Karate.Test
    Karate testSmoke() {
        return Karate.run("classpath:karate")
                     .tags("@smoke")
                     .relativeTo(getClass());
    }

    // Solo tests de autenticación
    @Karate.Test
    Karate testAuth() {
        return Karate.run("classpath:karate/auth")
                     .relativeTo(getClass());
    }

    // Solo tests de facturas
    @Karate.Test
    Karate testInvoices() {
        return Karate.run("classpath:karate/invoices")
                     .relativeTo(getClass());
    }
}