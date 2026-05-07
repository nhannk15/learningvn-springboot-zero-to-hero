package com.example.learningvn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.entity.Product;
import com.example.learningvn.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/controller")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product productDetails) {
        log.debug("Creating a new product...");
        Product product = productService.createProduct(productDetails);

        log.info("Successfully created a new product called: {}", product.getName());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        log.debug("Fetching all products...");
        List<Product> products = productService.getAllProducts();

        log.info("Successfully fetched {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        log.debug("Fetching a product...");
        Product product = productService.getProductById(id);

        log.info("Successfully fetched a product called: {}", product.getName());
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
            @RequestBody Product productDetails) {
        log.debug("Updating a product...");
        Product product = productService.updateProduct(id, productDetails);

        log.info("Successfully updated a product called: {}", product.getName());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("Deleting a product...");
        productService.deleteProduct(id);

        log.info("Successfully deleted a product with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam(name = "name") String name) {
        log.debug("Searching products by name...");
        List<Product> products = productService.searchProductsByName(name);
        
        log.info("Successfully searched {} products by name: {}", products.size(), name);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(name = "name") String name) {
        log.debug("Searching products by category...");
        List<Product> products = productService.getProductsByCategory(name);
        
        log.info("Successfully searched {} products by category: {}", products.size(), name);
        return ResponseEntity.ok(products);
    }

}
