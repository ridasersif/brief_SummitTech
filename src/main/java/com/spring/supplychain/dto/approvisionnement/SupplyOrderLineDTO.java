package com.spring.supplychain.dto.approvisionnement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class SupplyOrderLineDTO {

    private Long idLine;

    @NotNull(message = "Material ID is required")
    private Long materialId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @PositiveOrZero(message = "Price must be >= 0")
    private Double price;
}
