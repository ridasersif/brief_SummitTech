package com.spring.supplychain.mapper.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.BillOfMaterialDTO;
import com.spring.supplychain.model.approvisionnement.BillOfMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BillOfMaterialMapper {

    BillOfMaterialMapper INSTANCE = Mappers.getMapper(BillOfMaterialMapper.class);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "material", ignore = true)
    BillOfMaterial toEntity(BillOfMaterialDTO dto);

    @Mapping(source = "product.idProduct", target = "productId")
    @Mapping(source = "material.idMaterial", target = "materialId")
    BillOfMaterialDTO toDTO(BillOfMaterial entity);
}
