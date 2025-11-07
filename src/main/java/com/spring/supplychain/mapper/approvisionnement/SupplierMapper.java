package com.spring.supplychain.mapper.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplierDTO;
import com.spring.supplychain.model.approvisionnement.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupplierMapper {



    SupplierDTO toDTO(Supplier supplier);


    Supplier toEntity(SupplierDTO supplierDTO);
}
