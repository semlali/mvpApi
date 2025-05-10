package com.mvp.ordermanagement.service;

import com.mvp.ordermanagement.dto.RefundRequestDTO;
import com.mvp.ordermanagement.exception.InvalidRefundRequestException;
import com.mvp.ordermanagement.mapper.OrderMapper;
import com.mvp.ordermanagement.mapper.RefundRequestMapper;
import com.mvp.ordermanagement.model.OrderItem;
import com.mvp.ordermanagement.model.RefundRequest;
import com.mvp.ordermanagement.repository.OrderItemRepository;
import com.mvp.ordermanagement.repository.RefundRequestRepository;
import com.mvp.ordermanagement.service.impl.RefundServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefundServiceTest {

    @InjectMocks
    private RefundServiceImpl refundService;

    @Mock
    private RefundRequestRepository refundRequestRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private RefundRequestMapper refundRequestMapper;

    @Test
    void testRequestRefund_Success() {
        // Arrange
        RefundRequestDTO refundRequestDTO = new RefundRequestDTO();
        refundRequestDTO.setOrderItemId(1L);

        OrderItem item = new OrderItem();
        item.setId(1L);

        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderItem(item);

        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(refundRequestRepository.save(any(RefundRequest.class))).thenReturn(refundRequest);
        when(refundRequestMapper.toDto(any(RefundRequest.class))).thenReturn(refundRequestDTO);

        // Act
        RefundRequestDTO result = refundService.requestRefund(refundRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getOrderItemId());
    }

    @Test
    void testRequestRefund_OrderItemNotFound() {
        // Arrange
        RefundRequestDTO refundRequestDTO = new RefundRequestDTO();
        refundRequestDTO.setOrderItemId(1L);

        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidRefundRequestException.class, () ->
                refundService.requestRefund(refundRequestDTO)
        );
    }
}
