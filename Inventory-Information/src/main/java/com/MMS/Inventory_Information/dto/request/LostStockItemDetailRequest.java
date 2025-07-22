package com.MMS.Inventory_Information.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class LostStockItemDetailRequest {

    private UUID itemId;        // FK to item-service

    private Integer Quantity;

    private String description;
    private String duration;
    private String remark;
}
