package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.DisposalStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class FixedAssetDisposalRequest {


        private UUID tenantId;

        @NotNull(message = "Store ID is required")
        private UUID storeId;

        @NotNull(message = "Processed by ID is required")
        private UUID processedById;


        private String fixedAssetDisposalNo;

        private String processedBy; // Optional snapshot

        @NotNull(message = "Approved date is required")
        private LocalDate approvedDate;


        private DisposalStatus disposalStatus;


        private LocalDate processedOn;

        @NotNull(message = "Proposed date is required")
        private LocalDate proposedDate;


        private UUID disposableAssetId;

        @NotNull(message = "Disposal details must not be null")
        @Size(min = 1, message = "At least one disposal detail is required")
        @Valid
        private List<FixedAssetDisposalDetailRequest> disposalDetails;
}
