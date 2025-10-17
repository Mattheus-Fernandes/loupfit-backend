package com.loupfitorderservice.order_service.business.dto.consumable;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConsumablesQuantityDTO {

    private Integer quantity;
    private String operation;
}
