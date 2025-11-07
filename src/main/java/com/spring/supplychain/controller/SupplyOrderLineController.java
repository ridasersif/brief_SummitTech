package com.spring.supplychain.controller;

import com.spring.supplychain.dto.approvisionnement.SupplyOrderLineDTO;
import com.spring.supplychain.response.SuccessResponse;
import com.spring.supplychain.service.interfaces.approvisionnement.ISupplyOrderLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supply-order-lines")
@RequiredArgsConstructor
@Tag(name = "Supply Order Lines", description = "CRUD for supply order lines")
public class SupplyOrderLineController {

    private final ISupplyOrderLineService service;

    @PostMapping
    @Operation(summary = "Create a supply order line")
    public ResponseEntity<SuccessResponse<SupplyOrderLineDTO>> create(@Valid @RequestBody SupplyOrderLineDTO dto,
                                                                      HttpServletRequest request) {
        SupplyOrderLineDTO created = service.createLine(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SuccessResponse.of(HttpStatus.CREATED, "Supply order line created", created, request.getRequestURI()));
    }

    @GetMapping
    @Operation(summary = "Get all supply order lines")
    public ResponseEntity<SuccessResponse<List<SupplyOrderLineDTO>>> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "All supply order lines fetched",
                service.getAllLines(), request.getRequestURI()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supply order line by ID")
    public ResponseEntity<SuccessResponse<SupplyOrderLineDTO>> getById(@PathVariable Long id,
                                                                       HttpServletRequest request) {
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supply order line fetched",
                service.getLineById(id), request.getRequestURI()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a supply order line")
    public ResponseEntity<SuccessResponse<SupplyOrderLineDTO>> update(@PathVariable Long id,
                                                                      @Valid @RequestBody SupplyOrderLineDTO dto,
                                                                      HttpServletRequest request) {
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supply order line updated",
                service.updateLine(id, dto), request.getRequestURI()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a supply order line")
    public ResponseEntity<SuccessResponse<Void>> delete(@PathVariable Long id,
                                                        HttpServletRequest request) {
        service.deleteLine(id);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supply order line deleted",
                null, request.getRequestURI()));
    }
}
