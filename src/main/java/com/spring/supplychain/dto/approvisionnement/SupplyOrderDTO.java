package com.spring.supplychain.dto.approvisionnement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SupplyOrderDTO {

    private Long idOrder;

    @NotNull(message = "Order number is required")
    private String orderNumber;

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    private String status; // Enum as string

    @PositiveOrZero(message = "Total amount must be >= 0")
    private Double totalAmount;

    private List<SupplyOrderLineDTO> orderLines; // DTOs of lines
}
