package com.spring.supplychain.service.interfaces.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.BillOfMaterialDTO;

import java.util.List;

public interface IBillOfMaterialService {

    BillOfMaterialDTO createBOM(BillOfMaterialDTO dto);

    BillOfMaterialDTO updateBOM(Long id, BillOfMaterialDTO dto);

    void deleteBOM(Long id);

    BillOfMaterialDTO getBOMById(Long id);

    List<BillOfMaterialDTO> getAllBOMs();
}
