package com.MMS.Inventory_Information.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCountDetailResponse {
    private UUID id;
    private UUID itemId;
    private int quantity;
    private String remark;
}
