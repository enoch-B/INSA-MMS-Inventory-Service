package com.MMS.Inventory_Information.dto.response;

import com.MMS.Inventory_Information.dto.request.NeedAssessmentDetailRequest;
import com.MMS.Inventory_Information.enums.PurchaseType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class NeedAssessmentResponse {
     private UUID id;
     private UUID tenantId;
     private PurchaseType purchaseType;
    private UUID departmentId;
    private UUID storeId;
    private UUID budgetYearId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<NeedAssessmentDetailResponse> assessmentResponse;
}
