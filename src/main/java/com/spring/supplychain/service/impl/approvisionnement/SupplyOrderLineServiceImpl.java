package com.spring.supplychain.service.impl.approvisionnement;

import com.spring.supplychain.dto.approvisionnement.SupplyOrderLineDTO;
import com.spring.supplychain.exception.ResourceNotFoundException;
import com.spring.supplychain.mapper.approvisionnement.SupplyOrderLineMapper;
import com.spring.supplychain.model.approvisionnement.RawMaterial;
import com.spring.supplychain.model.approvisionnement.SupplyOrderLine;
import com.spring.supplychain.repository.RawMaterialRepository;
import com.spring.supplychain.repository.SupplyOrderLineRepository;
import com.spring.supplychain.service.interfaces.approvisionnement.ISupplyOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplyOrderLineServiceImpl implements ISupplyOrderLineService {

    private final SupplyOrderLineRepository lineRepository;
    private final RawMaterialRepository materialRepository;
    private final SupplyOrderLineMapper mapper;

    @Override
    public SupplyOrderLineDTO createLine(SupplyOrderLineDTO dto) {
        RawMaterial material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found"));

        SupplyOrderLine line = mapper.toEntity(dto);
        line.setRawMaterial(material);
        SupplyOrderLine saved = lineRepository.save(line);
        return mapper.toDTO(saved);
    }

    @Override
    public SupplyOrderLineDTO updateLine(Long id, SupplyOrderLineDTO dto) {
        SupplyOrderLine existing = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supply order line not found"));
        RawMaterial material = materialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Raw material not found"));
        existing.setQuantity(dto.getQuantity());
        existing.setRawMaterial(material);
        return mapper.toDTO(lineRepository.save(existing));
    }

    @Override
    public void deleteLine(Long id) {
        SupplyOrderLine existing = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supply order line not found"));
        lineRepository.delete(existing);
    }

    @Override
    public SupplyOrderLineDTO getLineById(Long id) {
        SupplyOrderLine existing = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supply order line not found"));
        return mapper.toDTO(existing);
    }

    @Override
    public List<SupplyOrderLineDTO> getAllLines() {
        return lineRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
