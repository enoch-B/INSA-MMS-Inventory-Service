package com.MMS.Inventory_Information.dto.response;


import lombok.Data;

import java.util.UUID;

@Data
public class LostItemDetailResponse {

    private UUID id;
    private UUID itemId;

    private String tagNo;               // Optional snapshot
    private Double bookValue;           // Optional snapshot
    private String accountCode;         // Optional snapshot

    private String duration;
    private String description;
    private String remark;

}
