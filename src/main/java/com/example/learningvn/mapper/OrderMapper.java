package com.example.learningvn.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.learningvn.model.dto.OrderDTO;
import com.example.learningvn.model.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "username", source = "user.username")
    OrderDTO toDTO(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    Order toEntity(OrderDTO dto);

    List<OrderDTO> toDTOList(List<Order> entities);
}
