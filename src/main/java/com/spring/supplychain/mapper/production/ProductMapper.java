package com.spring.supplychain.mapper.production;

import com.spring.supplychain.dto.production.ProductDTO;
import com.spring.supplychain.model.production.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product entity);
}
