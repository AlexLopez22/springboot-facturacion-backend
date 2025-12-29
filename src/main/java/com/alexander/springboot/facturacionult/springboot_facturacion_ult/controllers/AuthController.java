package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.LoginRequest;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.UserAccountRepository;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.securities.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserAccountRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    return usuarioRepository.findByCorreo(request.getCorreo())
        .filter(u -> passwordEncoder.matches(request.getContrasena(), u.getContrasena()))
        .map(u -> {
            String token = jwtUtil.generateToken(
                u.getCorreo(),
                u.getRol().getCodigo(),   // rol del usuario
                u.getEmpresaRuc()         // tenant (RUC de la empresa)
            );
            return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
        })
        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas"));
}


}
