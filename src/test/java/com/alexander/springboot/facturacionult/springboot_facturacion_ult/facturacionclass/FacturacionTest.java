package com.alexander.springboot.facturacionult.springboot_facturacion_ult.facturacionclass;

import com.intuit.karate.junit5.Karate;

class FacturacionTest {
    @Karate.Test
    Karate testFacturas() {
        return Karate.run("classpath:facturacion/factura");
    }
}
