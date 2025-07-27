package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class InventoryBalanceItemRequest {

    @NotNull(message = "Item ID is required")
    private UUID itemId;

    private String itemCode;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0", inclusive = true, message = "Quantity cannot be negative")
    private BigDecimal quantity;

    @NotNull(message = "Bin balance is required")
    @DecimalMin(value = "0", inclusive = true, message = "Bin balance cannot be negative")
    private BigDecimal binBalance;

    @NotNull(message = "Difference is required")
    private BigDecimal difference; // Difference can be negative, so no DecimalMin

    private String remark; // Optional, no validation needed
}
