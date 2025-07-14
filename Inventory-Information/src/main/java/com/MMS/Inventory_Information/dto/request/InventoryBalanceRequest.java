package com.MMS.Inventory_Information.dto.request;

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
    private UUID inventoryCountSheetId;
    private String storeMan;
    private String storeName;
    private String budgetYear;
    private String storeType;
    private String preparedBy;
    private LocalDate preparedOn;
    private List<InventoryBalanceItemRequest> items;
}
