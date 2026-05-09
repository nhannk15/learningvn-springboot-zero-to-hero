package com.example.learningvn.model.dto;

public class OrderDTO {
    private String orderNumber;
    private Double totalAmount;
    private String status;
    private String username;

    public OrderDTO() {
    }

    public OrderDTO(String orderNumber, Double totalAmount, String status, String username) {
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.status = status;
        this.username = username;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double gettotalAmount() {
        return totalAmount;
    }

    public void settotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OrderDTO [orderNumber=" + orderNumber + ", totalAmount=" + totalAmount + ", status=" + status
                + ", username=" + username + "]";
    }

}
