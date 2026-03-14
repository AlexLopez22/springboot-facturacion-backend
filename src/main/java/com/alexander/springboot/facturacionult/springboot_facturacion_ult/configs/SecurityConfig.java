package com.alexander.springboot.facturacionult.springboot_facturacion_ult.configs;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.securities.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
                // Configuración de autorización 
                .authorizeHttpRequests(auth -> auth                    
                        .requestMatchers("/api/auth/**", "/auth/**").permitAll()// Permitir acceso a la ruta sin necesidad de estar autenticado
                        .requestMatchers("/clients/sunat/**").permitAll()// Permitir acceso a la ruta sin necesidad de estar autenticado
                        .requestMatchers("/clients/reniec/**").permitAll()// Permitir acceso a la ruta sin necesidad de estar autenticado
                        .requestMatchers("/ubigeos").permitAll()// Permitir acceso a la ruta sin necesidad de estar autenticado
                        .requestMatchers("/clients/create-client").authenticated()  // Permitir acceso a la ruta de creación de clientes solo para usuarios autenticados 
                        .requestMatchers("/invoices/**").authenticated()// Permitir acceso a la ruta de creación de facturas solo para usuarios autenticados
                        .requestMatchers("/formas-pago/**").authenticated()
                        .requestMatchers("/products/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    // Configuración de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //Activar esto cuandos esuba el frondend en la nube
        //  configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        // Y comentar la linea de abajo , ya que funciona localmente
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8080"));// el  frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
