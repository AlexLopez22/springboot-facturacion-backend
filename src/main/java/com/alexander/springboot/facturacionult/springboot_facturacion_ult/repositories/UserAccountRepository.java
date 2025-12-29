package com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    Optional<UserAccount> findByCorreo(String correo);
}
