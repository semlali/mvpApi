package com.mvp.ordermanagement.controller;

import com.mvp.ordermanagement.dto.OrderDTO;
import com.mvp.ordermanagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    void testGetCustomerOrders_Success() throws Exception {
        // Arrange
        List<OrderDTO> orderDTOs = List.of(new OrderDTO(1L, LocalDate.now(), List.of("Product A")));

        when(orderService.getCustomerOrders(1L)).thenReturn(orderDTOs);

        // Act & Assert
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].productNames[0]").value("Product A"));
    }

    @Test
    void testGetCustomerOrders_EmptyList() throws Exception {
        // Arrange
        when(orderService.getCustomerOrders(2L)).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/orders/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
