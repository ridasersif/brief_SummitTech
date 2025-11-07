package com.spring.supplychain.service.impl.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.BillOfMaterialDTO;
import com.spring.supplychain.exception.ResourceNotFoundException;
import com.spring.supplychain.mapper.approvisionnement.BillOfMaterialMapper;
import com.spring.supplychain.model.approvisionnement.BillOfMaterial;
import com.spring.supplychain.model.production.Product;
import com.spring.supplychain.model.approvisionnement.RawMaterial;
import com.spring.supplychain.repository.BillOfMaterialRepository;
import com.spring.supplychain.repository.ProductRepository;
import com.spring.supplychain.repository.RawMaterialRepository;
import com.spring.supplychain.service.interfaces.approvisionnement.IBillOfMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BillOfMaterialServiceImpl implements IBillOfMaterialService {

    private final BillOfMaterialRepository bomRepository;
    private final ProductRepository productRepository;
    private final RawMaterialRepository materialRepository;
    private final BillOfMaterialMapper mapper;

    @Override
    public BillOfMaterialDTO createBOM(BillOfMaterialDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        RawMaterial material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found"));

        BillOfMaterial bom = mapper.toEntity(dto);
        bom.setProduct(product);
        bom.setMaterial(material);

        BillOfMaterial saved = bomRepository.save(bom);
        return mapper.toDTO(saved);
    }

    @Override
    public BillOfMaterialDTO updateBOM(Long id, BillOfMaterialDTO dto) {
        BillOfMaterial existing = bomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BOM not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        RawMaterial material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found"));

        existing.setProduct(product);
        existing.setMaterial(material);
        existing.setQuantity(dto.getQuantity());

        return mapper.toDTO(bomRepository.save(existing));
    }

    @Override
    public void deleteBOM(Long id) {
        BillOfMaterial existing = bomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BOM not found"));
        bomRepository.delete(existing);
    }

    @Override
    public BillOfMaterialDTO getBOMById(Long id) {
        return bomRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("BOM not found"));
    }

    @Override
    public List<BillOfMaterialDTO> getAllBOMs() {
        return bomRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
