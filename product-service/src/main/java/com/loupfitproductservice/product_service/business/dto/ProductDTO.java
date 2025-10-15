package com.loupfitproductservice.product_service.business.dto;

import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private BigDecimal costPrice;
    private Integer stock;
    private String category;
    private String subcategory;
    private String size;
    private String color;
    private String material;
    private Integer sales = 0;
    private String createdBy;
}
