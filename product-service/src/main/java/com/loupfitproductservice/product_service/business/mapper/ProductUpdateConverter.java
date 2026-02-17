package com.loupfitproductservice.product_service.business.mapper;

import com.loupfitproductservice.product_service.business.record.product.in.ProductPriceUpdateRequest;
import com.loupfitproductservice.product_service.business.record.product.in.ProductUpdateRequest;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductUpdateConverter {

    Product doUpdate(ProductUpdateRequest request, @MappingTarget Product entity);

    Product doUpdatePrice(ProductPriceUpdateRequest request, @MappingTarget Product entity);

}
