package com.mvp.ordermanagement.service;

import com.mvp.ordermanagement.dto.RefundRequestDTO;

public interface RefundService {
    /**
     * Request a refund for an order item.
     *
     * @param refundRequestDTO the refund request details
     * @return the created refund request
     */
    RefundRequestDTO requestRefund(RefundRequestDTO refundRequestDTO);
}
