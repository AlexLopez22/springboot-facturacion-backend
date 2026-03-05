package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.PaymentMethodDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.PaymentMethodService;

@RestController
@RequestMapping("/formas-pago")
@CrossOrigin("*")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping("/listar")
    public List<PaymentMethodDTO> listar() {
        return paymentMethodService.listar();
    }
}
