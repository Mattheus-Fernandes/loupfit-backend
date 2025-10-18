package com.loupfitorderservice.order_service.business.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    private String id;
    private String orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private String size;
    private String color;
    private String soldBy;
    private String paymentMethod;
}
