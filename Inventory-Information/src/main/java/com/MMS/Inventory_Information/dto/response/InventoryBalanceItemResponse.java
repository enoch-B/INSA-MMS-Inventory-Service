package com.MMS.Inventory_Information.dto.response;


import lombok.Data;

import java.math.BigDecimal;

import java.util.UUID;

@Data
public class InventoryBalanceItemResponse {
    private UUID id;
    private UUID itemId;
    private BigDecimal quantity;
    private BigDecimal binBalance;
    private BigDecimal difference;
    private String remark;
}
