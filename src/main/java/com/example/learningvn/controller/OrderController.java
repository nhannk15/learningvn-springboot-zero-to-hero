package com.example.learningvn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.dto.OrderDTO;
import com.example.learningvn.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createOrder(@PathVariable Long userId, @Valid @RequestBody OrderDTO orderDetails) {
        log.info("REST request creating a new order for user: {}", userId);
        OrderDTO dto = orderService.createOrder(userId, orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<?> findAllOrders() {
        log.info("REST request fetching all order");
        List<OrderDTO> orders = orderService.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findOrderById(@PathVariable long orderId) {
        log.info("REST request find order by id: {}", orderId);
        OrderDTO dto = orderService.findById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable Long userId) {
        log.info("REST request find order by id");
        List<OrderDTO> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok().body(orders);        
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateOrderStatus(
        @RequestParam(name = "orderId") Long orderId,
        @RequestParam(name = "status") String status) {
        log.info("REST request update order status by id {}", orderId);
        OrderDTO dto = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
    }
}
