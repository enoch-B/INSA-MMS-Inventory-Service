package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.DisposableType;
import com.MMS.Inventory_Information.enums.DisposalStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class DisposableAssetRequest {


      private UUID tenantId;

      private String drNo;

      @NotNull(message = "Store ID is required")
      private UUID storeId;

      @NotNull(message = "Department ID is required")
      private UUID departmentId;

      private UUID processedById;

      @NotNull(message = "Disposable type is required")
      private DisposableType disposableType;

      @NotNull(message = "Requisition date is required")
      private LocalDate requisitionDate;

      @NotNull(message = "Disposal status is required")
      private DisposalStatus disposalStatus;

      private String processedByName;

      private LocalDate processedOn;

      @NotNull(message = "Disposable asset details must not be null")
      @Size(min = 1, message = "At least one disposable asset detail is required")
      @Valid
      private List<DisposableFixedAssetDetailRequest> disposableFixedAssetDetails;
}
