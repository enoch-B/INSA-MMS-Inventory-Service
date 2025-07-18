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
    private UUID storeId;
    private UUID preparedById;
    private UUID committeeId;
    private String inventoryCountNumber;
    private String committeeName;
    private List<UUID> committeeMembersId;
    private List<String> committeeMembersName;
    private CountType countType;
    private StoreType storeType;
    private UUID budgetYearId;
    private LocalDate countDate;
    private String preparedBy;
    private LocalDate preparedOn;

    private List<InventoryCountDetailRequest> inventoryItems;
}
