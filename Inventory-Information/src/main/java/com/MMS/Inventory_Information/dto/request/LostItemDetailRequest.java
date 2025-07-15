package com.MMS.Inventory_Information.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LostItemDetailRequest {

    private UUID itemId;

    private String tagNo;               // Optional snapshot
    private Double bookValue;           // Optional snapshot
    private String accountCode;         // Optional snapshot

    private String duration;
    private String description;
    private String remark;
}
