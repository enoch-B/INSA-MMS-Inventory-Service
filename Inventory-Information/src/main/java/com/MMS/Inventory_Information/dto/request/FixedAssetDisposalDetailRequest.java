package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class FixedAssetDisposalDetailRequest {

    @NotNull(message = "Item ID is required")
    private UUID itemId;

    @NotBlank(message = "Tag number is required")
    private String tagNumber;

    @NotNull(message = "Gain/Loss Value ID is required")
    private UUID gainLossValueId;

    @NotNull(message = "Selling Price ID is required")
    private UUID sellingPriceId;

    @NotBlank(message = "Account code is required")
    private String accountCode;

    @NotBlank(message = "Book value is required")
    private String bookValue;

    @NotBlank(message = "Item location is required")
    private String itemLocation;

    @NotBlank(message = "Disposal method is required")
    private String disposalMethod;

    private String description; // Optional
}
