package com.loupfitproductservice.product_service.business.converter;

import com.loupfitproductservice.product_service.business.dto.ProductDTO;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter{

    Product productEntity(ProductDTO productDTO);

    ProductDTO productDTO(Product product);
}
