package com.loupfit.bffservice.business.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductUpdatePriceDTO {

    private BigDecimal price;
}
