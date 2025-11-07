package com.spring.supplychain.service.interfaces.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplyOrderLineDTO;
import java.util.List;

public interface ISupplyOrderLineService {

    SupplyOrderLineDTO createLine(SupplyOrderLineDTO dto);

    SupplyOrderLineDTO updateLine(Long id, SupplyOrderLineDTO dto);

    void deleteLine(Long id);

    SupplyOrderLineDTO getLineById(Long id);

    List<SupplyOrderLineDTO> getAllLines();
}
