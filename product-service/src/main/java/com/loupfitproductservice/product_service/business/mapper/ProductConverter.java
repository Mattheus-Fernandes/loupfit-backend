package com.loupfitproductservice.product_service.business.mapper;

import com.loupfitproductservice.product_service.business.record.product.in.ProductRequest;
import com.loupfitproductservice.product_service.business.record.product.out.ProductResponse;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductConverter{

    Product toEntity(ProductRequest request);

    ProductResponse toResponse(Product entity);

    List<ProductResponse> toResponseList(List<Product> entities);
}
