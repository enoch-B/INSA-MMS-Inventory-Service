package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.CountType;
import com.MMS.Inventory_Information.enums.StoreType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Setter
@Getter
public class InventoryCountRequest {

    @NotNull(message = "Tenant ID is required")
    private UUID tenantId;

    @NotNull(message = "Store ID is required")
    private UUID storeId;

    @NotNull(message = "Prepared By ID is required")
    private UUID preparedById;

    @NotNull(message = "Committee ID is required")
    private UUID committeeId;

    @NotBlank(message = "Inventory Count Number is required")
    private String inventoryCountNumber;

    @NotBlank(message = "Committee Name is required")
    private String committeeName;

    @NotNull(message = "Committee Members ID list must not be null")
    @Size(min = 1, message = "At least one committee member ID is required")
    private List<@NotNull(message = "Committee member ID cannot be null") UUID> committeeMembersId;

    @NotNull(message = "Committee Members Name list must not be null")
    @Size(min = 1, message = "At least one committee member name is required")
    private List<@NotBlank(message = "Committee member name cannot be blank") String> committeeMembersName;

    @NotNull(message = "Count Type is required")
    private CountType countType;

    @NotNull(message = "Store Type is required")
    private StoreType storeType;

    @NotNull(message = "Budget Year ID is required")
    private UUID budgetYearId;

    @NotNull(message = "Count Date is required")
    private LocalDate countDate;

    @NotBlank(message = "Prepared By is required")
    private String preparedBy;

    @NotNull(message = "Prepared On date is required")
    private LocalDate preparedOn;

    @NotNull(message = "Inventory items must not be null")
    @Size(min = 1, message = "At least one inventory item is required")
    @Valid
    private List<InventoryCountDetailRequest> inventoryItems;
}
