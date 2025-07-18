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
    private UUID tenantId;
    private String inventoryCountNumber;
    private UUID storeId;
    private UUID preparedById;
    private UUID committeeId;
    private String committeeName;
    private List<UUID> committeeMembersId;
    private List<String> committeeMembersName;
    private CountType countType;
    private StoreType storeType;
    private UUID budgetYearId;
    private LocalDate countDate;
    private String preparedBy;
    private LocalDate preparedOn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<InventoryCountDetailResponse> inventoryDetails;
}
