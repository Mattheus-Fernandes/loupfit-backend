package com.loupfitassetservice.asset_service.infrastructure.entity;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("asset")
public class Asset {

    @Id
    private String id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal costValue;
    private String placePurchase;
    private String createdBy;

}
