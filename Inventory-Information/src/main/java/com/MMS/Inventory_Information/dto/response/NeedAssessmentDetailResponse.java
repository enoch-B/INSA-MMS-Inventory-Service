package com.MMS.Inventory_Information.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class NeedAssessmentDetailResponse {
    private UUID id;
    private UUID itemId;
    private UUID generalLedger;
    private BigDecimal budgetAmount;
}
