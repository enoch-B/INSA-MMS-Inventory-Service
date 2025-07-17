package com.MMS.Inventory_Information.dto.request;


import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Data
public class FixedAssetDisposalDetailRequest {
    private UUID itemId;
    private String tagNumber;
    private UUID gainLossValueId;
    private UUID sellingPriceId;
    private String accountCode;
    private String bookValue;

    private String itemLocation;
    private String disposalMethod; // e.g., "Sale", "Donation", "Scrap"
    private String description; // Optional, for additional context



}
