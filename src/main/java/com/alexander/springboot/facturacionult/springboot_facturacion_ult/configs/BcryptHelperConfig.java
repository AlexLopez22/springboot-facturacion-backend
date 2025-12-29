package com.alexander.springboot.facturacionult.springboot_facturacion_ult.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * One-time helper to print a BCrypt hash. Remove after use.
 */
@Configuration
public class BcryptHelperConfig {

    @Bean
    public PasswordEncoder passwordEncoderHelper() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner bcryptPrinter(PasswordEncoder encoder) {
        return args -> {
            String raw = "Contraseña Aquí";
            System.out.println("Encriptada : " + encoder.encode(raw));
        };
    }
}
