package com.MMS.Inventory_Information.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class StockDisposalDetailResponse {
    private UUID id;
    private UUID itemId;
    private String disposalMethod;
    private String description;
    private java.math.BigDecimal sellingPrice;
    private LocalDate expirationDate;
}
