package com.loupfitconsumablesservice.consumables_service.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("consumable")
public class Consumables {

    @Id
    private String id;
    private String consumableName;
    private String description;
    private BigDecimal costValue;
    private Integer quantity;
    private String supplier;
    private String purchaseLink;
    private String createdby;
}
