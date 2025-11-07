package com.spring.supplychain.mapper.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplyOrderLineDTO;
import com.spring.supplychain.model.approvisionnement.SupplyOrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplyOrderLineMapper {

    SupplyOrderLineMapper INSTANCE = Mappers.getMapper(SupplyOrderLineMapper.class);

    @Mapping(source = "rawMaterial.idMaterial", target = "materialId")
    SupplyOrderLineDTO toDTO(SupplyOrderLine entity);

    @Mapping(source = "materialId", target = "rawMaterial.idMaterial")
    SupplyOrderLine toEntity(SupplyOrderLineDTO dto);

    List<SupplyOrderLineDTO> toDTOs(List<SupplyOrderLine> entities);
    List<SupplyOrderLine> toEntities(List<SupplyOrderLineDTO> dtos);
}
