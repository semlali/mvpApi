package com.mvp.ordermanagement.service.impl;

import com.mvp.ordermanagement.dto.RefundRequestDTO;
import com.mvp.ordermanagement.exception.InvalidRefundRequestException;
import com.mvp.ordermanagement.mapper.OrderMapper;
import com.mvp.ordermanagement.mapper.RefundRequestMapper;
import com.mvp.ordermanagement.model.OrderItem;
import com.mvp.ordermanagement.model.RefundRequest;
import com.mvp.ordermanagement.repository.OrderItemRepository;
import com.mvp.ordermanagement.repository.RefundRequestRepository;
import com.mvp.ordermanagement.service.RefundService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RefundServiceImpl implements RefundService {

    private final RefundRequestRepository refundRequestRepository;

    private final OrderItemRepository orderItemRepository;

    private final RefundRequestMapper refundRequestMapper;


    public RefundServiceImpl(RefundRequestRepository refundRequestRepository, OrderItemRepository orderItemRepository, RefundRequestMapper refundRequestMapper) {
        this.refundRequestRepository = refundRequestRepository;
        this.orderItemRepository = orderItemRepository;
        this.refundRequestMapper = refundRequestMapper;
    }

    @Override
    public RefundRequestDTO requestRefund(RefundRequestDTO refundRequestDTO) {

        RefundRequest refundRequest = new RefundRequest();
        OrderItem item = orderItemRepository.findById(refundRequestDTO.getOrderItemId())
                .orElseThrow(() -> new InvalidRefundRequestException("Order item not found"));
        refundRequest.setOrderItem(item);
        refundRequest.setStatus("PENDING");
        refundRequest.setRequestDate(LocalDate.now());

        refundRequestRepository.save(refundRequest);

        return refundRequestMapper.toDto(refundRequest);
    }
}