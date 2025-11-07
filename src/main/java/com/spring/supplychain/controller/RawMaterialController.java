package com.spring.supplychain.controller;

import com.spring.supplychain.dto.approvisionnement.RawMaterialDTO;
import com.spring.supplychain.response.SuccessResponse;
import com.spring.supplychain.service.interfaces.approvisionnement.IRawMaterialService;
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
@RequestMapping("/api/raw-materials")
@RequiredArgsConstructor
@Tag(name = "Raw Materials", description = "CRUD operations for raw materials")
public class RawMaterialController {

    private final IRawMaterialService rawMaterialService;

    @Operation(summary = "Create a new raw material")
    @PostMapping
    public ResponseEntity<SuccessResponse<RawMaterialDTO>> createRawMaterial(
            @Valid @RequestBody RawMaterialDTO rawMaterialDTO,
            HttpServletRequest request) {

        RawMaterialDTO created = rawMaterialService.createRawMaterial(rawMaterialDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SuccessResponse.of(HttpStatus.CREATED, "Raw material created successfully", created, request.getRequestURI()));
    }

    @Operation(summary = "Get all raw materials")
    @GetMapping
    public ResponseEntity<SuccessResponse<List<RawMaterialDTO>>> getAllRawMaterials(HttpServletRequest request) {
        List<RawMaterialDTO> rawMaterials = rawMaterialService.getAllRawMaterials();
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "All raw materials fetched successfully", rawMaterials, request.getRequestURI()));
    }

    @Operation(summary = "Get raw material by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<RawMaterialDTO>> getRawMaterialById(
            @PathVariable Long id,
            HttpServletRequest request) {

        RawMaterialDTO rawMaterial = rawMaterialService.getRawMaterialById(id);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Raw material fetched successfully", rawMaterial, request.getRequestURI()));
    }

    @Operation(summary = "Update raw material by ID")
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<RawMaterialDTO>> updateRawMaterial(
            @PathVariable Long id,
            @Valid @RequestBody RawMaterialDTO rawMaterialDTO,
            HttpServletRequest request) {

        RawMaterialDTO updated = rawMaterialService.updateRawMaterial(id, rawMaterialDTO);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Raw material updated successfully", updated, request.getRequestURI()));
    }

    @Operation(summary = "Delete raw material by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteRawMaterial(
            @PathVariable Long id,
            HttpServletRequest request) {

        rawMaterialService.deleteRawMaterial(id);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Raw material deleted successfully", null, request.getRequestURI()));
    }

    @Operation(summary = "Add suppliers to raw material")
    @PostMapping("/{id}/suppliers")
    public ResponseEntity<SuccessResponse<RawMaterialDTO>> addSuppliers(
            @PathVariable Long id,
            @RequestBody List<Long> supplierIds,
            HttpServletRequest request) {

        RawMaterialDTO updated = rawMaterialService.addSuppliersToMaterial(id, supplierIds);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Suppliers added successfully", updated, request.getRequestURI()));
    }

    @Operation(summary = "Remove supplier from raw material")
    @DeleteMapping("/{id}/suppliers/{supplierId}")
    public ResponseEntity<SuccessResponse<RawMaterialDTO>> removeSupplier(
            @PathVariable Long id,
            @PathVariable Long supplierId,
            HttpServletRequest request) {

        RawMaterialDTO updated = rawMaterialService.removeSupplierFromMaterial(id, supplierId);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supplier removed successfully", updated, request.getRequestURI()));
    }
}
