package com.mvp.ordermanagement.mapper;

import com.mvp.ordermanagement.dto.OrderDTO;
import com.mvp.ordermanagement.dto.RefundRequestDTO;
import com.mvp.ordermanagement.model.Order;
import com.mvp.ordermanagement.model.OrderItem;
import com.mvp.ordermanagement.model.RefundRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO dto =  new OrderDTO(order.getId(), order.getOrderDate(), null);

        // Récupérer les noms des produits des OrderItems associés à la commande
        List<String> productNames = order.getItems().stream()
                .map(OrderItem::getProductName)
                .collect(Collectors.toList());

        dto.setProductNames(productNames);

        return dto;
    }

    public List<OrderDTO> toDtoList(List<Order> orders) {
        if (orders == null) {
            return Collections.emptyList();
        }
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

}