package com.alexander.springboot.facturacionult.springboot_facturacion_ult.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alexander.springboot.facturacionult.springboot_facturacion_ult.dtos.ProductDTO;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.entities.Product;
import com.alexander.springboot.facturacionult.springboot_facturacion_ult.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCodigo(product.getCodigo());
        dto.setDescripcion(product.getDescripcion());
        dto.setUnidadMedida(product.getUnidadMedida());
        dto.setAfectacionIgv(product.getAfectacionIgv());
        dto.setEstado(product.getEstado());
        return dto;
    }

    public List<ProductDTO> listProduct() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }
        
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public ProductDTO listProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado id: " + id));

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCodigo(product.getCodigo());
        dto.setDescripcion(product.getDescripcion());
        dto.setUnidadMedida(product.getUnidadMedida());
        dto.setAfectacionIgv(product.getAfectacionIgv());
        dto.setEstado(product.getEstado());

        return dto;
    }
}
