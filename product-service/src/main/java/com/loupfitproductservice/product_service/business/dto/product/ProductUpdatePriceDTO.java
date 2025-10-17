package com.loupfitproductservice.product_service.business.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductUpdatePriceDTO {

    private BigDecimal price;
}
