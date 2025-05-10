package com.mvp.ordermanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private List<String> productNames;

    public <OrderDTO> OrderDTO(long l, LocalDate now, List<String> es) {
        this.id = l;
        this.orderDate = now;
        this.productNames = es;
    }


}
