package com.MMS.Inventory_Information.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class FixedAssetReturnDetailRequest {
    private UUID itemId;
    private String itemStatus;
    private String bookValue;
    private String accountCode;
    private String tagNumber;
    private String description;
}
