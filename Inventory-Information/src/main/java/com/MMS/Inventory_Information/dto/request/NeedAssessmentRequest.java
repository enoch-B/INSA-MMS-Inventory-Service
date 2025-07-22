package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.PurchaseType;
import com.MMS.Inventory_Information.model.NeedAssessment.NeedAssessmentDetail;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class NeedAssessmentRequest {
      private UUID tenantId;
      private PurchaseType purchaseType;
      private UUID departmentId;
      private UUID storeId;
      private UUID budgetYearId;

      private List<NeedAssessmentDetailRequest> assessmentDetail;
}
