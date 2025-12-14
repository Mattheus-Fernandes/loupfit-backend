package com.loupfit.bffservice.business.dto.out;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssetDTO {

    private String id;
    private String assetName;
    private String description;
    private Integer quantity;
    private BigDecimal costValue;
    private String supplier;
    private String createdBy;
}
