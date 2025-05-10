package com.mvp.ordermanagement.controller;

import com.mvp.ordermanagement.dto.RefundRequestDTO;
import com.mvp.ordermanagement.service.RefundService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refund")
public class RefundController {

    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    /**
     * Request a refund for an order item.
     *
     * @param orderItemId the ID of the order item to refund
     * @return the created refund request
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{orderItemId}")
    public RefundRequestDTO requestRefund(@PathVariable Long orderItemId) {
        RefundRequestDTO refundRequestDTO = new RefundRequestDTO();
        refundRequestDTO.setOrderItemId(orderItemId);
        return refundService.requestRefund(refundRequestDTO);
    }
}
