package com.MMS.Inventory_Information.dto.request;

import java.util.UUID;

public class InventoryBalanceItemRequest {
    private UUID itemId; // Reference to Item from item-service
    private String itemCode;
    private int quantity;
    private int binBalance;
    private int difference;
    private String remark;
}
