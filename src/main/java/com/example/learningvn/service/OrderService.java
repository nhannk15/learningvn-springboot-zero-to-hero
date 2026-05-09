package com.example.learningvn.service;

import java.util.List;

import com.example.learningvn.model.dto.OrderDTO;

public interface OrderService {

    OrderDTO createOrder(Long userId, OrderDTO orderDetails);
    OrderDTO findById(Long id);
    List<OrderDTO> findAll();
    List<OrderDTO> getOrdersByUser(Long userId);
    OrderDTO updateOrderStatus(Long orderId, String status);

}
