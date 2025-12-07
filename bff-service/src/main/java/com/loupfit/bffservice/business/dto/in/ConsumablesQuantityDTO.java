package com.loupfit.bffservice.business.dto.in;

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
