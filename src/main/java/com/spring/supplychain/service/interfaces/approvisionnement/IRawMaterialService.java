package com.spring.supplychain.service.interfaces.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.RawMaterialDTO;

import java.util.List;

public interface IRawMaterialService {

    RawMaterialDTO createRawMaterial(RawMaterialDTO rawMaterialDTO);

    RawMaterialDTO updateRawMaterial(Long id, RawMaterialDTO rawMaterialDTO);

    void deleteRawMaterial(Long id);

    RawMaterialDTO getRawMaterialById(Long id);

    List<RawMaterialDTO> getAllRawMaterials();

    RawMaterialDTO addSuppliersToMaterial(Long materialId, List<Long> supplierIds);

    RawMaterialDTO removeSupplierFromMaterial(Long materialId, Long supplierId);

}
