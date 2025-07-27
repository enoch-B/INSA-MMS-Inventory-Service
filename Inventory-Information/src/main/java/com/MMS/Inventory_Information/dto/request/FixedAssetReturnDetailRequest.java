package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class FixedAssetReturnDetailRequest {

    @NotNull(message = "Item ID is required")
    private UUID itemId;

    @NotBlank(message = "Item status is required")
    private String itemStatus;

    @NotBlank(message = "Book value is required")
    private String bookValue;

    @NotBlank(message = "Account code is required")
    private String accountCode;

    @NotBlank(message = "Tag number is required")
    private String tagNumber;

}
