package com.MMS.Inventory_Information.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class DisposableFixedAssetDetailResponse {
    private UUID id;
    private UUID itemId;
    private String tagNumber;
    private String accountCode;
    private BigDecimal bookValue;

    private String description;
    private Integer quantity;
    private LocalDate expirationDate;
    private String batchNo;
}
