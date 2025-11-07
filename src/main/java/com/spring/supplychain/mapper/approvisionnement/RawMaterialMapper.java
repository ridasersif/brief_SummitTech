package com.spring.supplychain.mapper.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.RawMaterialDTO;
import com.spring.supplychain.model.approvisionnement.RawMaterial;
import com.spring.supplychain.model.approvisionnement.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RawMaterialMapper {

    RawMaterialMapper INSTANCE = Mappers.getMapper(RawMaterialMapper.class);


    @Mapping(target = "supplierIds", source = "suppliers", qualifiedByName = "mapSuppliersToIds")
    RawMaterialDTO toDTO(RawMaterial rawMaterial);


    @Mapping(target = "suppliers", ignore = true)
    RawMaterial toEntity(RawMaterialDTO rawMaterialDTO);


    @Named("mapSuppliersToIds")
    default List<Long> mapSuppliersToIds(List<Supplier> suppliers) {
        if (suppliers == null) return null;
        return suppliers.stream()
                .map(Supplier::getIdSupplier)
                .collect(Collectors.toList());
    }
}
