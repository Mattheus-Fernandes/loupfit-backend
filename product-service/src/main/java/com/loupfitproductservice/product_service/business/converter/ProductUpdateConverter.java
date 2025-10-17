package com.loupfitproductservice.product_service.business.converter;

import com.loupfitproductservice.product_service.business.dto.product.ProductUpdateJsonDTO;
import com.loupfitproductservice.product_service.business.dto.product.ProductUpdatePriceDTO;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductUpdateConverter {

    void productUpdateJson(ProductUpdateJsonDTO dto, @MappingTarget Product entity);

    void productUpdatePrice(ProductUpdatePriceDTO price, @MappingTarget Product entity);

}
