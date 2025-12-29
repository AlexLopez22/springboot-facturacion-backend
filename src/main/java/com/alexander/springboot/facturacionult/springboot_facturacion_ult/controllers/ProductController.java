package com.alexander.springboot.facturacionult.springboot_facturacion_ult.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ProductDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Product;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.services.ProductService;

@RestController
@RequestMapping("/productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> listProduct() {
        return productService.listProduct();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product producto) {
        return productService.saveProduct(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO dto = productService.getProductById(id);
        return ResponseEntity.ok(dto);
    }
    

}
