package com.mvp.ordermanagement.service.impl;


import com.mvp.ordermanagement.dto.OrderDTO;
import com.mvp.ordermanagement.mapper.OrderMapper;
import com.mvp.ordermanagement.repository.OrderRepository;
import com.mvp.ordermanagement.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getCustomerOrders(Long customerId) {
        return orderMapper.toDtoList(orderRepository.findByCustomerId(customerId));
    }
}