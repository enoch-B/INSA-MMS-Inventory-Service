package com.MMS.Inventory_Information.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class InventoryBalanceItemRequest {
    private UUID itemId; // Reference to Item from item-service
    private String itemCode;
    private BigDecimal quantity;
    private BigDecimal binBalance;
    private BigDecimal difference;
    private String remark;
}
