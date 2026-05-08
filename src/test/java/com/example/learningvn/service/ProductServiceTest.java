package com.example.learningvn.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.learningvn.mapper.ProductMapper;
import com.example.learningvn.model.dto.ProductDTO;
import com.example.learningvn.model.entity.Product;
import com.example.learningvn.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;
    
    @InjectMocks
    private ProductServiceImpl service;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product(
                null,
                "iPhone 15",
                "Điện thoại iPhone 15 128GB",
                new BigDecimal("24990000"),
                50,
                "Điện thoại",
                null,
                null);
        product2 = new Product(
                null,
                "MacBook Air M2",
                "Laptop Apple MacBook Air M2 2023",
                new BigDecimal("32990000"),
                30,
                "Laptop",
                null,
                null);
    }

    @Test
    void testGetAllProducts_WhenProductsExist_ShouldReturnProductList() {
        //--- Arrange (Prepare data).
        List<Product> expectedProducts = Arrays.asList(product1, product2);

        //--- Mock Repository Behavior.
        when(repository.findAll()).thenReturn(expectedProducts);

        //--- Act (Execute the need-testing method)
        List<ProductDTO> actualProduct = service.getAllProducts();

        assertNotNull(actualProduct);
        assertEquals(2, actualProduct.size());
        assertEquals("Laptop", actualProduct.get(1).getName());

        //--- Verify repository was called.
        verify(repository, times(1)).findAll();
    }

}
