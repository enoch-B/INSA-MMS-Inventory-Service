package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.StoreType;
import jakarta.validation.Valid;
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
@Valid
public class InventoryBalanceRequest {
    private UUID tenantId;
    private UUID preparedById;
    private String preparedBy;
    private LocalDate preparedOn;
    private UUID inventoryCountId;
    private StoreType storeType;

    private List<InventoryBalanceItemRequest> items;
}
