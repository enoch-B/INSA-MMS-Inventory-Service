package com.MMS.Inventory_Information.dto.request;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@AllArgsConstructor
@Valid
@Getter
@Setter
public class InventoryCountDetailRequest {
    private UUID itemId;  // From item-service
    private String itemCode;
    private String itemName;
    private String unitMeasure;
    private int quantity;
    private String remark;

}
