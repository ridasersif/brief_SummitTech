package com.spring.supplychain.service.interfaces.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplierDTO;
import java.util.List;

public interface ISupplierService {

    SupplierDTO createSupplier(SupplierDTO supplierDTO);

    SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO);

    void deleteSupplier(Long id);

    SupplierDTO getSupplierById(Long id);

    List<SupplierDTO> getAllSuppliers();
}
