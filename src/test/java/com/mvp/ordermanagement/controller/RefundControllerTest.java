package com.mvp.ordermanagement.controller;

import com.mvp.ordermanagement.TestSecurityConfig;
import com.mvp.ordermanagement.dto.RefundRequestDTO;
import com.mvp.ordermanagement.exception.InvalidRefundRequestException;
import com.mvp.ordermanagement.service.RefundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
class RefundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RefundService refundService;



    @Test
    void testRequestRefund_Success() throws Exception {
        // Arrange
        RefundRequestDTO requestDTO = new RefundRequestDTO();
        requestDTO.setOrderItemId(1L);
        requestDTO.setReason("Product damaged");
        RefundRequestDTO responseDTO = new RefundRequestDTO();
        responseDTO.setOrderItemId(1L);
        responseDTO.setReason("Refund requested for product: Product A");

        // Mock du service
        when(refundService.requestRefund(any(RefundRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/refund/{orderItemId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderItemId").value(1L))
                .andExpect(jsonPath("$.reason").value("Refund requested for product: Product A"));
    }

    @Test
    void testRequestRefund_InvalidRequest() throws Exception {
        // Arrange
        when(refundService.requestRefund(any(RefundRequestDTO.class)))
                .thenThrow(new InvalidRefundRequestException("Order item not found"));

        // Act & Assert
        mockMvc.perform(post("/api/refund/{orderItemId}", 99L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Order item not found"));
    }
}
