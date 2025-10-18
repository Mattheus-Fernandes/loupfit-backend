package com.loupfitorderservice.order_service.business.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    private String productName;
    private Integer quantity;
    private String operation;
    private String inventory;
    private String paymentMethod;
}
