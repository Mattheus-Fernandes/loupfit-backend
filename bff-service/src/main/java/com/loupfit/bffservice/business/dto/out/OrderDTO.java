package com.loupfit.bffservice.business.dto.out;

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
    private String imageUrl;
    private Integer quantity;
    private BigDecimal totalPrice;
    private String size;
    private String color;
    private String soldBy;
    private String paymentMethod;
}
