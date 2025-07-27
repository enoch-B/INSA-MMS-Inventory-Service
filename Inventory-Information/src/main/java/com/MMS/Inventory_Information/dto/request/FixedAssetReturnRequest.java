package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class FixedAssetReturnRequest {

    @NotNull(message = "Tenant ID is required")
    private UUID tenantId;

    @NotNull(message = "Department ID is required")
    private UUID departmentId;

    @NotNull(message = "Store ID is required")
    private UUID storeId;

    @NotNull(message = "Processed By ID is required")
    private UUID processedById;

    @NotNull(message = "Returned By ID is required")
    private UUID returnedById;

    @NotBlank(message = "Processed By name is required")
    private String processedBy;

    @NotBlank(message = "Returned By name is required")
    private String returnedBy;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Received date is required")
    private LocalDate receivedDate;

    @NotNull(message = "Returned date is required")
    private LocalDate returnedDate;

    @NotNull(message = "Processed on date is required")
    private LocalDate processedOn;

    @NotNull(message = "Return details must not be null")
    @Size(min = 1, message = "At least one return detail is required")
    @Valid
    private List<FixedAssetReturnDetailRequest> returnDetails;
}
