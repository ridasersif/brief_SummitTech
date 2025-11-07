package com.spring.supplychain.dto.approvisionnement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillOfMaterialDTO {

    private Long idBOM;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Material ID is required")
    private Long materialId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
}
