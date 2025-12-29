package com.alexander.springboot.facturacionult.springboot_facturacion_ult.securities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.tenants.TenantContext;

import org.springframework.lang.NonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

/**
 * Filtro que lee el JWT del header Authorization y setea el SecurityContext.
 * Además, coloca el tenantDb en el TenantContext para el enrutamiento dinámico.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final Key key;

    public JwtAuthFilter(@Value("${security.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain)
            throws ServletException, IOException {

        System.out.println("JwtAuthFilter ejecutándose para: " + request.getRequestURI());

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                //para verificar el token recibido por consola
                System.out.println("Authorization header: " + header);
                System.out.println("Token recibido: " + token);
                System.out.println("Claims: " + claims);

                String username = claims.getSubject();
                String role = claims.get("role", String.class);
                String tenantDb = claims.get("tenantDb", String.class);

                // Construimos la autenticación con el rol
                var auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Guardamos tenant en request y en TenantContext
                request.setAttribute("tenantDb", tenantDb);
                TenantContext.setTenant(tenantDb);

                // Seteamos el SecurityContext
                org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (JwtException e) {
                System.out.println("Token inválido: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }

        // Continuamos con la cadena de filtros
        try {
            chain.doFilter(request, response);
        } finally {
            // Limpieza del TenantContext al terminar el request
            TenantContext.clear();
        }
    }
}
