package com.MMS.Inventory_Information.dto.response;

import com.MMS.Inventory_Information.enums.CountType;
import com.MMS.Inventory_Information.enums.StoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCountResponse {
    private UUID id;
    private String inventoryCountNumber;

    private UUID tenantId;
    private UUID storeId;

    private String budgetYear;
    private CountType countType;
    private StoreType storeType;

    private UUID committeeId;
    private String committeeName;
    private List<UUID> committeeMemberIds;
    private List<String> committeeMemberName;

    private String preparedBy;
    private LocalDate preparedOn;
    private LocalDate countDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<InventoryCountDetailResponse> inventoryDetails;
}
