package com.MMS.Inventory_Information.dto.response;


import lombok.Data;

import java.util.UUID;

@Data
public class LostStockItemDetailResponse {
    private UUID id;
    private UUID itemId;        // FK to item-service

    private Integer Quantity;

    private String description;
    private String duration;
    private String remark;


}
