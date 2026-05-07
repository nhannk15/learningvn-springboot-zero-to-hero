package com.example.learningvn.service;

import java.util.List;

import com.example.learningvn.model.entity.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> searchProductsByName(String name);
    List<Product> getProductsByCategory(String category);
}
