package com.mvp.ordermanagement.controller;

import com.mvp.ordermanagement.dto.OrderDTO;
import com.mvp.ordermanagement.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Get all orders for a specific customer.
     *
     * @param customerId the ID of the customer
     * @return the list of orders for the customer
     */
    @GetMapping("/{customerId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<OrderDTO> getCustomerOrders(@PathVariable Long customerId) {
        logger.info("Received request to get orders for customer ID: {}", customerId);
        List<OrderDTO> orders = orderService.getCustomerOrders(customerId);
        logger.info("Returning {} orders for customer ID: {}", orders.size(), customerId);
        return orders;
    }
}
