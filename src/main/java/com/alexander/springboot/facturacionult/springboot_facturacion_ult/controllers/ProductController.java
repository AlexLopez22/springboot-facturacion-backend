package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ProductDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Product;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list-product")
    public ResponseEntity<List<ProductDTO>> listProduct() {
        return ResponseEntity.ok(productService.listProduct());
    }

    @GetMapping("/list-product/{id}")
    public ResponseEntity<ProductDTO> listProductById(@PathVariable Long id) {
        ProductDTO dto = productService.listProductById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.createProduct(product);
        return ResponseEntity.ok(saved);
    }
}
