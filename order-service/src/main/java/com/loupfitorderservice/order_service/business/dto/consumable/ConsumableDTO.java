package com.loupfitorderservice.order_service.business.dto.consumable;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConsumableDTO {

    private String id;
    private String consumableName;
    private String description;
    private BigDecimal costValue;
    private Integer quantity;
    private String supplier;
    private String purchaseLink;
    private String createdby;
}
