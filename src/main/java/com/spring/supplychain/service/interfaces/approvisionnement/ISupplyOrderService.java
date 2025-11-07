package com.spring.supplychain.service.interfaces.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplyOrderDTO;

import java.util.List;

public interface ISupplyOrderService {

    SupplyOrderDTO createSupplyOrder(SupplyOrderDTO dto);

    SupplyOrderDTO updateSupplyOrder(Long id, SupplyOrderDTO dto);

    SupplyOrderDTO getSupplyOrderById(Long id);

    List<SupplyOrderDTO> getAllSupplyOrders();

    void deleteSupplyOrder(Long id);
}
