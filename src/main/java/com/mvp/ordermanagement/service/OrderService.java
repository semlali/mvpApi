package com.mvp.ordermanagement.service;


import com.mvp.ordermanagement.dto.OrderDTO;
import com.mvp.ordermanagement.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    /**
     * Create a new order.
     *
     * @param customerId the order to create
     * @return the created order
     */
    List<OrderDTO> getCustomerOrders(Long customerId) throws OrderNotFoundException;
}
