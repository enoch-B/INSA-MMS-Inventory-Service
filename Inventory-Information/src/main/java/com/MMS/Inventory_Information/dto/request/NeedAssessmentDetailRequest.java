package com.MMS.Inventory_Information.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class NeedAssessmentDetailRequest {
     private UUID itemId;
     private UUID generalLedger;
     private BigDecimal budgetAmount;
}
