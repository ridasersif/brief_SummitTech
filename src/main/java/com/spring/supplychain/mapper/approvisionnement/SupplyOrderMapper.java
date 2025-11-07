package com.spring.supplychain.mapper.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplyOrderDTO;
import com.spring.supplychain.dto.approvisionnement.SupplyOrderLineDTO;
import com.spring.supplychain.model.approvisionnement.SupplyOrder;
import com.spring.supplychain.model.approvisionnement.SupplyOrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplyOrderMapper {

    SupplyOrderMapper INSTANCE = Mappers.getMapper(SupplyOrderMapper.class);

    // Mapping SupplyOrder -> DTO
    @Mapping(source = "supplier.idSupplier", target = "supplierId")
    SupplyOrderDTO toDTO(SupplyOrder entity);

    // Mapping SupplyOrderLine -> DTO
    @Mapping(source = "rawMaterial.idMaterial", target = "materialId")
    SupplyOrderLineDTO toLineDTO(SupplyOrderLine entity);

    // Mapping DTO -> SupplyOrder
    @Mapping(source = "supplierId", target = "supplier.idSupplier")
    SupplyOrder toEntity(SupplyOrderDTO dto);

    // Mapping DTO -> SupplyOrderLine
    @Mapping(source = "materialId", target = "rawMaterial.idMaterial")
    SupplyOrderLine toLineEntity(SupplyOrderLineDTO dto);

    // List mappings
    List<SupplyOrderDTO> toDTOs(List<SupplyOrder> entities);
    List<SupplyOrderLineDTO> toLineDTOs(List<SupplyOrderLine> entities);
}
