package com.MMS.Inventory_Information.dto.response;


import lombok.Data;

import java.util.UUID;

@Data
public class FixedAssetTransferDetailResponse {
    private UUID id;
    private UUID itemId; // Reference to Item from item-service
    private String tagNumber; // Reference to Tag from fixed-asset-service
    private Double bookValue;
    private String accountCode;
    private Integer quantity;
    private String remark;
    private String description;
}
