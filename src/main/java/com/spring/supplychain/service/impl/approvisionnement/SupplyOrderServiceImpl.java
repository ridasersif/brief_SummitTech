package com.spring.supplychain.service.impl.approvisionnement;
import com.spring.supplychain.dto.approvisionnement.SupplyOrderDTO;
import com.spring.supplychain.enums.SupplyOrderStatus;
import com.spring.supplychain.mapper.approvisionnement.SupplyOrderMapper;
import com.spring.supplychain.model.approvisionnement.SupplyOrder;
import com.spring.supplychain.model.approvisionnement.SupplyOrderLine;
import com.spring.supplychain.model.approvisionnement.Supplier;
import com.spring.supplychain.repository.SupplyOrderRepository;
import com.spring.supplychain.repository.SupplierRepository;
import com.spring.supplychain.service.interfaces.approvisionnement.ISupplyOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyOrderServiceImpl implements ISupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final SupplierRepository supplierRepository;
    private final SupplyOrderMapper mapper;

    @Override
    @Transactional
    public SupplyOrderDTO createSupplyOrder(SupplyOrderDTO dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

        SupplyOrder order = new SupplyOrder();
        order.setOrderNumber(dto.getOrderNumber());
        order.setSupplier(supplier);
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(SupplyOrderStatus.valueOf(dto.getStatus()));

        // Map order lines
        List<SupplyOrderLine> lines = dto.getOrderLines().stream()
                .map(lineDTO -> {
                    SupplyOrderLine line = mapper.toLineEntity(lineDTO);
                    line.setSupplyOrder(order);
                    return line;
                }).toList();

        order.setOrderLines(lines);

        SupplyOrder savedOrder = supplyOrderRepository.save(order);
        return mapper.toDTO(savedOrder);
    }

    @Override
    @Transactional
    public SupplyOrderDTO updateSupplyOrder(Long id, SupplyOrderDTO dto) {
        SupplyOrder order = supplyOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SupplyOrder not found"));

        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

        order.setOrderNumber(dto.getOrderNumber());
        order.setSupplier(supplier);
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(SupplyOrderStatus.valueOf(dto.getStatus()));

        // Clear old lines and set new ones
        order.getOrderLines().clear();
        List<SupplyOrderLine> lines = dto.getOrderLines().stream()
                .map(lineDTO -> {
                    SupplyOrderLine line = mapper.toLineEntity(lineDTO);
                    line.setSupplyOrder(order);
                    return line;
                }).toList();

        order.getOrderLines().addAll(lines);

        SupplyOrder updatedOrder = supplyOrderRepository.save(order);
        return mapper.toDTO(updatedOrder);
    }

    @Override
    public SupplyOrderDTO getSupplyOrderById(Long id) {
        SupplyOrder order = supplyOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SupplyOrder not found"));
        return mapper.toDTO(order);
    }

    @Override
    public List<SupplyOrderDTO> getAllSupplyOrders() {
        List<SupplyOrder> orders = supplyOrderRepository.findAll();
        return mapper.toDTOs(orders);
    }

    @Override
    public void deleteSupplyOrder(Long id) {
        SupplyOrder order = supplyOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SupplyOrder not found"));
        supplyOrderRepository.delete(order);
    }
}
