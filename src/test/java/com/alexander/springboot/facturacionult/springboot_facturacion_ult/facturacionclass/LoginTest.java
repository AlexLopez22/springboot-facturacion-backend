package com.alexander.springboot.facturacionult.springboot_facturacion_ult.facturacionclass;

import com.intuit.karate.junit5.Karate;

class LoginTest {
    @Karate.Test
    Karate testLogin() {
        return Karate.run("classpath:facturacion/login");
    }
}
 