package com.example.learningvn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.dto.ProductDTO;
import com.example.learningvn.model.entity.Product;
import com.example.learningvn.service.ProductService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDTO productDetails,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        log.debug("Creating a new product...");
        ProductDTO createdProduct = productService.createProduct(productDetails);

        log.info("Successfully created a new product called: {}", createdProduct.getName());
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.debug("Fetching all products...");
        List<ProductDTO> products = productService.getAllProducts();

        log.info("Successfully fetched {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        log.debug("Fetching a product...");
        ProductDTO product = productService.getProductById(id);

        log.info("Successfully fetched a product called: {}", product.getName());
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
            @RequestBody ProductDTO productDetails) {
        log.debug("Updating a product...");
        ProductDTO product = productService.updateProduct(id, productDetails);

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
    public ResponseEntity<List<ProductDTO>> getProductsByName(@RequestParam(name = "name") String name) {
        log.debug("Searching products by name...");
        List<ProductDTO> products = productService.searchProductsByName(name);

        log.info("Successfully searched {} products by name: {}", products.size(), name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam(name = "name") String name) {
        log.debug("Searching products by category...");
        List<ProductDTO> products = productService.getProductsByCategory(name);

        log.info("Successfully searched {} products by category: {}", products.size(), name);
        return ResponseEntity.ok(products);
    }

}
