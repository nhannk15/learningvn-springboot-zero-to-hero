package com.example.learningvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningvn.exception.OrderNotFoundException;
import com.example.learningvn.exception.UserNotFoundException;
import com.example.learningvn.mapper.OrderMapper;
import com.example.learningvn.model.dto.OrderDTO;
import com.example.learningvn.model.entity.Order;
import com.example.learningvn.model.entity.User;
import com.example.learningvn.repository.OrderRepository;
import com.example.learningvn.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper mapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDTO createOrder(Long userId, OrderDTO orderDetails) {
        log.debug("SERVICE: creating a new order");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Can't found user with id: " + userId));
        log.debug("SERVICE: found user with id: " + user);
        Order newOrder = mapper.toEntity(orderDetails);
        newOrder.setUser(user);
        Order createdOrder = orderRepository.save(newOrder);
        log.debug("SERVICE: successfully created a new order: {}", newOrder.getOrderNumber());
        return mapper.toDTO(createdOrder);
    }

    @Override
    public OrderDTO findById(Long id) {
        log.debug("SERVICE: finding order with id: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: {}" + id));
        log.debug("SERVICE: found order with id: {}", id);
        return mapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> findAll() {
        log.debug("SERVICE: fetching all orders");
        List<Order> orders = orderRepository.findAll();
        log.debug("SERVICE: successfully fetched all orders");
        return mapper.toDTOList(orders);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        log.debug("SERVICE: fetching all orders with user id: {}", userId);
        List<Order> orders = orderRepository.findByUserId(userId);
        log.debug("SERVICE: successfully fetched all orders with user id: {}", userId);
        return mapper.toDTOList(orders);
    }

    @Override
    public OrderDTO updateOrderStatus(Long orderId, String status) {
        log.debug("SERVICE: finding order with id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: {}" + orderId));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        log.debug("SERVICE: successfully updated order with id: {}", orderId);
        return mapper.toDTO(updatedOrder);
    }

}
