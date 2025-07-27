package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.PurchaseType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class NeedAssessmentRequest {

      @NotNull(message = "Tenant ID is required")
      private UUID tenantId;

      @NotNull(message = "Purchase type is required")
      private PurchaseType purchaseType;

      @NotNull(message = "Department ID is required")
      private UUID departmentId;

      @NotNull(message = "Store ID is required")
      private UUID storeId;

      @NotNull(message = "Budget Year ID is required")
      private UUID budgetYearId;

      @NotNull(message = "Assessment details must not be null")
      @Size(min = 1, message = "At least one assessment detail is required")
      @Valid
      private List<NeedAssessmentDetailRequest> assessmentDetail;
}
