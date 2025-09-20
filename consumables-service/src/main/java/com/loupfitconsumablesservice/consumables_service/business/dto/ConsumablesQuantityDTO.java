package com.loupfitconsumablesservice.consumables_service.business.dto;

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
