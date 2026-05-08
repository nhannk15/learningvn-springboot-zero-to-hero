package com.example.learningvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningvn.exception.ProductNotFoundException;
import com.example.learningvn.mapper.ProductMapper;
import com.example.learningvn.model.dto.ProductDTO;
import com.example.learningvn.model.entity.Product;
import com.example.learningvn.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    public final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product createdProduct = productMapper.toEntity(product);
        productRepository.save(createdProduct);
        return productMapper.toDTO(createdProduct);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productMapper.updateProductFromDto(productDetails, existingProduct);
        Product updateProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        List<Product> products = productRepository.findByNameIgnoreCase(name);
        return productMapper.toDTOList(products);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return productMapper.toDTOList(products);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products =  productRepository.findAll();
        return productMapper.toDTOList(products);
    }

}
