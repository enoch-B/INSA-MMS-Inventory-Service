package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class DisposableFixedAssetDetailRequest {

    @NotNull(message = "Item ID is required")
    private UUID itemId;

    @NotBlank(message = "Tag number is required")
    private String tagNumber;

    @NotBlank(message = "Account code is required")
    private String accountCode;

    @NotNull(message = "Book value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Book value must be greater than zero")
    private BigDecimal bookValue;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private LocalDate expirationDate;

    private String batchNo; // Optional — can be left as-is
}
