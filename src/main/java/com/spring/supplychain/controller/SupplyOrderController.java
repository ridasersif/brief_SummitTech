package com.spring.supplychain.controller;

import com.spring.supplychain.dto.approvisionnement.SupplyOrderDTO;
import com.spring.supplychain.response.SuccessResponse;
import com.spring.supplychain.service.interfaces.approvisionnement.ISupplyOrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supply-orders")
@RequiredArgsConstructor
public class SupplyOrderController {

    private final ISupplyOrderService supplyOrderService;

    @PostMapping
    public ResponseEntity<SuccessResponse<SupplyOrderDTO>> createSupplyOrder(@RequestBody SupplyOrderDTO dto, HttpServletRequest request) {
        SupplyOrderDTO created = supplyOrderService.createSupplyOrder(dto);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supply order created successfully", created, request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<SupplyOrderDTO>>> getAllSupplyOrders(HttpServletRequest request) {
        List<SupplyOrderDTO> orders = supplyOrderService.getAllSupplyOrders();
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "All supply orders fetched successfully", orders, request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<SupplyOrderDTO>> getSupplyOrderById(@PathVariable Long id, HttpServletRequest request) {
        SupplyOrderDTO order = supplyOrderService.getSupplyOrderById(id);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supply order fetched successfully", order, request.getRequestURI()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<SupplyOrderDTO>> updateSupplyOrder(@PathVariable Long id,
                                                                             @RequestBody SupplyOrderDTO dto,
                                                                             HttpServletRequest request) {
        SupplyOrderDTO updated = supplyOrderService.updateSupplyOrder(id, dto);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supply order updated successfully", updated, request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteSupplyOrder(@PathVariable Long id, HttpServletRequest request) {
        supplyOrderService.deleteSupplyOrder(id);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supply order deleted successfully", null, request.getRequestURI()));
    }
}
