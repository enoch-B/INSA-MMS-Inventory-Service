package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LostItemDetailRequest {

    @NotNull(message = "Item ID is required")
    private UUID itemId;

    // Optional snapshot fields —
    private String tagNo;

    @PositiveOrZero(message = "Book value must be zero or positive")
    private Double bookValue;

    private String accountCode;

    @NotBlank(message = "Duration is required")
    private String duration;

    @NotBlank(message = "Description is required")
    private String description;

    private String remark; // Optional
}
