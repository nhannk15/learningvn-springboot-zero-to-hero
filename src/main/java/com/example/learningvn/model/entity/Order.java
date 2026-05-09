package com.example.learningvn.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", columnDefinition = "VARCHAR(100)", nullable = false)
    @NotBlank
    private String orderNumber;

    @Column(name = "total_amount", nullable = false)
    @Min(value = 0, message = "Total amount must be greater than 0")
    private Double totalAmount;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_status")
    @NotBlank
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void onCreate() {
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Order() {
    }

    public Order(@NotBlank String orderNumber,
            @Min(value = 0, message = "Total amount must be greater than 0") Double totalAmount,
            LocalDateTime orderDate, @NotBlank String status, User user) {
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
    }

    public Order(Long id, @NotBlank String orderNumber,
            @Min(value = 0, message = "Total amount must be greater than 0") Double totalAmount,
            LocalDateTime orderDate, @NotBlank String status, User user) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderNumber=" + orderNumber + ", totalAmount=" + totalAmount + ", orderDate="
                + orderDate + ", status=" + status + ", user=" + user + "]";
    }

}
