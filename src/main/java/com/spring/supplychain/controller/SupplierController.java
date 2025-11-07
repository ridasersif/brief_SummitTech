package com.spring.supplychain.controller;

import com.spring.supplychain.dto.approvisionnement.SupplierDTO;
import com.spring.supplychain.response.SuccessResponse;
import com.spring.supplychain.service.interfaces.approvisionnement.ISupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final ISupplierService supplierService;

    @Operation(summary = "Create a new supplier", description = "Create a new supplier in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier created successfully", content = @Content(schema = @Schema(implementation = SupplierDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicate supplier", content = @Content)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<SupplierDTO>> createSupplier(@RequestBody SupplierDTO supplierDTO, HttpServletRequest request) {
        SupplierDTO created = supplierService.createSupplier(supplierDTO);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supplier created successfully", created, request.getRequestURI()));
    }

    @Operation(summary = "Get all suppliers", description = "Retrieve all suppliers from the system")
    @GetMapping
    public ResponseEntity<SuccessResponse<List<SupplierDTO>>> getAllSuppliers(HttpServletRequest request) {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "All suppliers fetched successfully", suppliers, request.getRequestURI()));
    }

    @Operation(summary = "Get supplier by ID", description = "Retrieve a supplier by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier fetched successfully", content = @Content(schema = @Schema(implementation = SupplierDTO.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<SupplierDTO>> getSupplierById(@PathVariable Long id, HttpServletRequest request) {
        SupplierDTO supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supplier fetched successfully", supplier, request.getRequestURI()));
    }

    @Operation(summary = "Update supplier", description = "Update an existing supplier by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier updated successfully", content = @Content(schema = @Schema(implementation = SupplierDTO.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<SupplierDTO>> updateSupplier(@PathVariable Long id,
                                                                       @RequestBody SupplierDTO supplierDTO,
                                                                       HttpServletRequest request) {
        SupplierDTO updated = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supplier updated successfully", updated, request.getRequestURI()));
    }

    @Operation(summary = "Delete supplier", description = "Delete a supplier by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Supplier not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteSupplier(@PathVariable Long id, HttpServletRequest request) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(SuccessResponse.of(HttpStatus.OK, "Supplier deleted successfully", null, request.getRequestURI()));
    }
}
