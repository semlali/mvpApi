package com.mvp.ordermanagement.service;

import com.mvp.ordermanagement.dto.OrderDTO;
import com.mvp.ordermanagement.mapper.OrderMapper;
import com.mvp.ordermanagement.model.Order;
import com.mvp.ordermanagement.repository.OrderRepository;
import com.mvp.ordermanagement.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

     @Mock
     private OrderRepository orderRepository;

     @InjectMocks
     private OrderServiceImpl orderService;

     @Mock
     private OrderMapper orderMapper;

     @Test
     public void testGetCustomerOrders() {
            Long customerId = 1L;
             List<OrderDTO> mockOrderDTOs = new ArrayList<>();
             mockOrderDTOs.add(new OrderDTO(1L, null, null));
             when(orderMapper.toDtoList(List.of(Order.builder().id(1L).build()))).thenReturn(mockOrderDTOs);
             when(orderRepository.findByCustomerId(customerId)).thenReturn(List.of(Order.builder().id(1L).build()));
             List<OrderDTO> result = orderService.getCustomerOrders(customerId);
            // Verify that the orderRepository method was called
             verify(orderRepository).findByCustomerId(customerId);
            int expectedSize = 1;
             assertEquals(expectedSize, result.size());
     }
}
