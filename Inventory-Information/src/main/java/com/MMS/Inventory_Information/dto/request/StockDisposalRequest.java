package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class StockDisposalRequest {

    @NotNull(message = "Tenant ID is required")
    private UUID tenantId;

    @NotNull(message = "Store ID is required")
    private UUID storeId;

    @NotNull(message = "Processed By ID is required")
    private UUID processedById;

    @NotBlank(message = "Processed By is required")
    private String processedBy;

    @NotBlank(message = "Disposal number is required")
    private String disposalNo;

    @NotBlank(message = "Disposal status is required")
    private String disposalStatus;

    @NotNull(message = "Propose date is required")
    private LocalDate proposeDate;

    @NotNull(message = "Approved date is required")
    private LocalDate approvedDate;

    @NotNull(message = "Proposed on date is required")
    private LocalDate proposedOn;

    @NotBlank(message = "File name is required")
    private String fileName;

    @NotBlank(message = "File type is required")
    private String fileType;

    @NotNull(message = "File data is required")
    private byte[] fileData;

    @NotNull(message = "Stock disposal details must not be null")
    @Size(min = 1, message = "At least one stock disposal detail is required")
    @Valid
    private List<StockDisposalDetailRequest> stockDisposalDetails;
}
