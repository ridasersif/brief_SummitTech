package com.spring.supplychain.controller;

import com.spring.supplychain.dto.approvisionnement.BillOfMaterialDTO;
import com.spring.supplychain.service.interfaces.approvisionnement.IBillOfMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bom")
@RequiredArgsConstructor
public class BillOfMaterialController {

    private final IBillOfMaterialService bomService;


    @PostMapping
    public ResponseEntity<BillOfMaterialDTO> createBOM(@RequestBody BillOfMaterialDTO dto) {
        BillOfMaterialDTO created = bomService.createBOM(dto);
        return ResponseEntity.ok(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BillOfMaterialDTO> updateBOM(
            @PathVariable Long id,
            @RequestBody BillOfMaterialDTO dto
    ) {
        BillOfMaterialDTO updated = bomService.updateBOM(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBOM(@PathVariable Long id) {
        bomService.deleteBOM(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<BillOfMaterialDTO> getBOMById(@PathVariable Long id) {
        BillOfMaterialDTO dto = bomService.getBOMById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BillOfMaterialDTO>> getAllBOMs() {
        List<BillOfMaterialDTO> dtos = bomService.getAllBOMs();
        return ResponseEntity.ok(dtos);
    }
}
