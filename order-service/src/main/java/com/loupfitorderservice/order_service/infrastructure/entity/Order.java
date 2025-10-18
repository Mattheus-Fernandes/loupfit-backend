package com.loupfitorderservice.order_service.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("sales")
public class Order {

    @Id
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
