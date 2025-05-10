package com.mvp.ordermanagement.mapper;
import com.mvp.ordermanagement.dto.RefundRequestDTO;
import com.mvp.ordermanagement.model.RefundRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RefundRequestMapper {

    public RefundRequestDTO toDto(RefundRequest refundRequest) {
        if (refundRequest == null) {
            return null;
        }

        RefundRequestDTO dto = new RefundRequestDTO();
        dto.setOrderItemId(refundRequest.getOrderItem().getId());
        dto.setReason(refundRequest.getStatus());
        return dto;
    }

    // Méthode pour convertir une liste de RefundRequest en une liste de RefundRequestDTO
    public List<RefundRequestDTO> toDtoList(List<RefundRequest> refundRequests) {
        if (refundRequests == null) {
            return Collections.emptyList();
        }
        return refundRequests.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
