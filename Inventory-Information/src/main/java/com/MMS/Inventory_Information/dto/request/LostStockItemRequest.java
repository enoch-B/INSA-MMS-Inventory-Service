package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class LostStockItemRequest {

    @NotNull(message = "Tenant ID is required")
    private UUID tenantId;

    @NotBlank(message = "Lost Stock Item Number is required")
    private String lostStockItemNo;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Registration Date is required")
    private LocalDate registrationDate;

    @NotNull(message = "Region ID is required")
    private UUID regionId;

    @NotNull(message = "Department ID is required")
    private UUID departmentId;

    @NotNull(message = "Store ID is required")
    private UUID storeId;

    @NotNull(message = "Committee ID is required")
    private UUID committeeId;

    @NotBlank(message = "Committee Name is required")
    private String committeeName;

    @NotNull(message = "Committee Members ID list must not be null")
    @Size(min = 1, message = "At least one committee member ID is required")
    private List<@NotNull(message = "Committee member ID cannot be null") UUID> committeeMembersId;

    @NotNull(message = "Committee Members Name list must not be null")
    @Size(min = 1, message = "At least one committee member name is required")
    private List<@NotBlank(message = "Committee member name cannot be blank") String> committeeMembersName;

    @NotNull(message = "Processed By ID is required")
    private UUID processedById;

    private String processedBy; // Optional snapshot

    @NotNull(message = "Processed On date is required")
    private LocalDate processedOn;

    @NotBlank(message = "File Name is required")
    private String fileName;

    @NotBlank(message = "File Type is required")
    private String fileType;

    @NotNull(message = "File data is required")
    private byte[] data;

    @NotNull(message = "Lost stock item details must not be null")
    @Size(min = 1, message = "At least one lost stock item detail is required")
    @Valid
    private List<LostStockItemDetailRequest> lostStockItemDetailRequest;
}
