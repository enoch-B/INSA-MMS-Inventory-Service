package com.MMS.Inventory_Information.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class StockDisposalDetailRequest {
    private UUID itemId;
    private String disposalMethod;
    private String description;
    private BigDecimal sellingPrice;
    private LocalDate expirationDate;
}
