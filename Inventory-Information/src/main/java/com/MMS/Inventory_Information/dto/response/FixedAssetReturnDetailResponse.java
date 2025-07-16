package com.MMS.Inventory_Information.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class FixedAssetReturnDetailResponse {
    private UUID itemId;
    private String itemStatus;
    private String bookValue;
    private String accountCode;
    private String tagNumber;
    private String description;
}
