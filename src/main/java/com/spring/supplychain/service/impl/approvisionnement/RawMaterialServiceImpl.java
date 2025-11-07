package com.spring.supplychain.service.impl.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.RawMaterialDTO;
import com.spring.supplychain.exception.ResourceInUseException;
import com.spring.supplychain.exception.ResourceNotFoundException;
import com.spring.supplychain.mapper.approvisionnement.RawMaterialMapper;
import com.spring.supplychain.model.approvisionnement.RawMaterial;
import com.spring.supplychain.model.approvisionnement.Supplier;
import com.spring.supplychain.repository.RawMaterialRepository;
import com.spring.supplychain.repository.SupplierRepository;
import com.spring.supplychain.service.interfaces.approvisionnement.IRawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RawMaterialServiceImpl implements IRawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;
    private final RawMaterialMapper rawMaterialMapper;
    private final SupplierRepository supplierRepository;

    @Override
    public RawMaterialDTO createRawMaterial(RawMaterialDTO rawMaterialDTO) {
        RawMaterial rawMaterial = rawMaterialMapper.toEntity(rawMaterialDTO);

        if (rawMaterialDTO.getSupplierIds() != null && !rawMaterialDTO.getSupplierIds().isEmpty()) {
            List<Supplier> suppliers = supplierRepository.findAllById(rawMaterialDTO.getSupplierIds());
            if (suppliers.size() != rawMaterialDTO.getSupplierIds().size()) {
                throw new ResourceNotFoundException("One or more supplier IDs are invalid");
            }
            rawMaterial.setSuppliers(suppliers);
        }

        RawMaterial saved = rawMaterialRepository.save(rawMaterial);
        return rawMaterialMapper.toDTO(saved);
    }

    @Override
    public RawMaterialDTO updateRawMaterial(Long id, RawMaterialDTO rawMaterialDTO) {
        RawMaterial existing = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found with id: " + id));

        existing.setName(rawMaterialDTO.getName());
        existing.setDescription(rawMaterialDTO.getDescription());
        existing.setStock(rawMaterialDTO.getStock());
        existing.setReservedStock(rawMaterialDTO.getReservedStock());
        existing.setStockMin(rawMaterialDTO.getStockMin());
        existing.setUnit(rawMaterialDTO.getUnit());

        if (rawMaterialDTO.getSupplierIds() != null) {
            List<Supplier> suppliers = supplierRepository.findAllById(rawMaterialDTO.getSupplierIds());
            if (suppliers.size() != rawMaterialDTO.getSupplierIds().size()) {
                throw new ResourceNotFoundException("One or more supplier IDs are invalid");
            }
            existing.setSuppliers(suppliers);
        }

        RawMaterial updated = rawMaterialRepository.save(existing);
        return rawMaterialMapper.toDTO(updated);
    }

    @Override
    public void deleteRawMaterial(Long id) {
        RawMaterial existing = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found with id: " + id));

        if (!existing.getSupplyOrderLines().isEmpty()) {
            throw new ResourceInUseException("Cannot delete raw material because it is used in orders");
        }

        rawMaterialRepository.delete(existing);
    }

    @Override
    public RawMaterialDTO getRawMaterialById(Long id) {
        RawMaterial existing = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found with id: " + id));
        return rawMaterialMapper.toDTO(existing);
    }

    @Override
    public List<RawMaterialDTO> getAllRawMaterials() {
        return rawMaterialRepository.findAll().stream()
                .map(rawMaterialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RawMaterialDTO addSuppliersToMaterial(Long materialId, List<Long> supplierIds) {
        RawMaterial material = rawMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));

        List<Supplier> suppliers = supplierRepository.findAllById(supplierIds);
        if (suppliers.size() != supplierIds.size()) {
            throw new ResourceNotFoundException("One or more supplier IDs are invalid");
        }
        material.getSuppliers().addAll(suppliers);
        RawMaterial saved = rawMaterialRepository.save(material);
        return rawMaterialMapper.toDTO(saved);
    }

    @Override
    public RawMaterialDTO removeSupplierFromMaterial(Long materialId, Long supplierId) {
        RawMaterial material = rawMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        material.getSuppliers().remove(supplier);
        RawMaterial saved = rawMaterialRepository.save(material);
        return rawMaterialMapper.toDTO(saved);
    }
}
