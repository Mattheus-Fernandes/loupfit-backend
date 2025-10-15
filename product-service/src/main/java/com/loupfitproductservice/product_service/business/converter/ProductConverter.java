package com.loupfitproductservice.product_service.business.converter;

import com.loupfitproductservice.product_service.business.dto.ProductDTO;
import com.loupfitproductservice.product_service.infrastructure.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductConverter{

    Product productEntity(ProductDTO productDTO);

    ProductDTO productDTO(Product product);

    List<ProductDTO> productsDTOList(List<Product> products);
}
