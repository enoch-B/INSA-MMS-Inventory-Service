package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.StoreType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryBalanceRequest {

    @NotNull(message = "Tenant ID is required")
    private UUID tenantId;

    @NotNull(message = "Prepared By ID is required")
    private UUID preparedById;

    @NotBlank(message = "Prepared By is required")
    private String preparedBy;

    @NotNull(message = "Prepared On date is required")
    private LocalDate preparedOn;

    @NotNull(message = "Inventory Count ID is required")
    private UUID inventoryCountId;

    @NotNull(message = "Store Type is required")
    private StoreType storeType;

    @NotNull(message = "Inventory balance items must not be null")
    @Size(min = 1, message = "At least one inventory balance item is required")
    @Valid
    private List<InventoryBalanceItemRequest> inventoryBalanceItemRequest;
}
