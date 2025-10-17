package com.loupfitproductservice.product_service.business.dto.product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductUpdateStockSalesDTO {

    private Integer quantity;
    private String operation;
    private String inventory;
}
