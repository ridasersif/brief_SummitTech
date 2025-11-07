package com.spring.supplychain.dto.production;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long idProduct;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotNull(message = "Production time is required")
    @Min(value = 1, message = "Production time must be greater than 0")
    private Integer productionTime;

    @NotNull(message = "Cost is required")
    @Min(value = 0, message = "Cost must be positive")
    private BigDecimal cost;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be positive")
    private Integer stock;

    @NotNull(message = "Minimum stock is required")
    @Min(value = 0, message = "Minimum stock must be positive")
    private Integer minimumStock;

    @NotBlank(message = "Unit is required")
    private String unit;
}
