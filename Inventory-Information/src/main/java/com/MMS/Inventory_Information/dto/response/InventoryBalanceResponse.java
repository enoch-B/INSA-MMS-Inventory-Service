package com.MMS.Inventory_Information.dto.response;

import com.MMS.Inventory_Information.enums.StoreType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class InventoryBalanceResponse {
    private UUID id;

    private UUID tenantId;

    private UUID inventoryCountId;

    private UUID preparedById;
    private String preparedBy;

    private StoreType storeType;

    private LocalDate preparedOn;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<InventoryBalanceItemResponse> inventoryBalanceItemResponse;
}
