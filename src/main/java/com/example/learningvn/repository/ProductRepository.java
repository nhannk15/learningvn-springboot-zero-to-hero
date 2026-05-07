package com.example.learningvn.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learningvn.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * We use customized CRUD.
     */
    List<Product> findByNameIgnoreCase(String name);
    List<Product> findByCategory(String category);
    List<Product> findByPriceLessThanEqual(BigDecimal price);    
}
