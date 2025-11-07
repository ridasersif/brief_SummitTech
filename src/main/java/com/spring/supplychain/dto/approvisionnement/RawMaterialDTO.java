package com.spring.supplychain.dto.approvisionnement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialDTO {

    private Long idMaterial;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be >= 0")
    private Integer stock;

    @NotNull(message = "Reserved stock is required")
    @Min(value = 0, message = "Reserved stock must be >= 0")
    private Integer reservedStock;

    @NotNull(message = "Minimum stock is required")
    @Min(value = 0, message = "Minimum stock must be >= 0")
    private Integer stockMin;

    @NotBlank(message = "Unit is required")
    private String unit;

    private List<Long> supplierIds;
}
