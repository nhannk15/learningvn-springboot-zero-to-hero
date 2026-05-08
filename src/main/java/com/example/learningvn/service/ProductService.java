package com.example.learningvn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.learningvn.model.dto.ProductDTO;
import com.example.learningvn.model.entity.Product;

@Service
public interface ProductService {
    ProductDTO createProduct(Product product);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductDTO productDetails);
    void deleteProduct(Long id);
    List<ProductDTO> searchProductsByName(String name);
    List<ProductDTO> getProductsByCategory(String category);
}
