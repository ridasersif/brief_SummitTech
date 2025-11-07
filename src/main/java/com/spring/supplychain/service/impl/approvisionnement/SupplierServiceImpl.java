package com.spring.supplychain.service.impl.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplierDTO;
import com.spring.supplychain.exception.ResourceNotFoundException;
import com.spring.supplychain.mapper.approvisionnement.SupplierMapper;
import com.spring.supplychain.model.approvisionnement.Supplier;
import com.spring.supplychain.repository.SupplierRepository;
import com.spring.supplychain.service.interfaces.approvisionnement.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements ISupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        Supplier saved = supplierRepository.save(supplier);
        return supplierMapper.toDTO(saved);
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        existing.setName(supplierDTO.getName());
        existing.setEmail(supplierDTO.getEmail());
        existing.setPhone(supplierDTO.getPhone());

        Supplier updated = supplierRepository.save(existing);
        return supplierMapper.toDTO(updated);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
        supplierRepository.delete(existing);
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
        return supplierMapper.toDTO(existing);
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }
}
