package com.example.learningvn.model.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    @Column(name = "name", columnDefinition = "NVARCHAR(100)", nullable = false)
    @NotBlank(message = "Product name can't be left blank")
    @Size(min = 5, max = 100, message = "Product Name must between 5 - 100 characters")
    private String name;

    private String description;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    @Min(value = 0, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotBlank(message = "Product name can't be left blank")
    @Size(min = 5, max = 100, message = "Product category must between 5 - 200 characters")
    private String category;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, BigDecimal price, Integer quantity, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductDTO [name=" + name + ", description=" + description + ", price=" + price + ", quantity="
                + quantity + ", category=" + category + "]";
    }

}
