package com.mvp.ordermanagement.dto;

import com.mvp.ordermanagement.model.RefundRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefundRequestDTO {
    private Long orderItemId;
    private String reason;

    public RefundRequestDTO(long l, String requested) {
        this.orderItemId = l;
        this.reason = requested;
    }
}
