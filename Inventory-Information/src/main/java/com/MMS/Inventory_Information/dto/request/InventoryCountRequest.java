package com.MMS.Inventory_Information.dto.request;


import com.MMS.Inventory_Information.enums.CountType;
import com.MMS.Inventory_Information.enums.StoreType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Valid
@AllArgsConstructor
@Setter
@Getter
public class InventoryCountRequest {
    private UUID tenantId;
    private String inventoryCountNo;
    private UUID storeId;
    private String storeName;
    private String storeMan;
    private String budgetYear;
    private StoreType storeType; // Enum recommended: INTERNAL / MERCHANDISED
   private CountType countType; // Enum recommended: PERIODIC / PERPETUAL

    private UUID committeeId;
    private List<UUID> committeeMemberIds;

    private String preparedBy;
    private LocalDate preparedOn;

    private List<InventoryCountDetailRequest> inventoryItems;
}
