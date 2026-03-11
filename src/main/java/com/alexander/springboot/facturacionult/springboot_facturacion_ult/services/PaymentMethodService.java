package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.PaymentMethodDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.PaymentMethodRepository;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethodDTO> listar() {
        return paymentMethodRepository.findAll()
            .stream()
            .map(pm -> new PaymentMethodDTO(pm.getId(), pm.getTipo()))
            .toList();
    }
}
