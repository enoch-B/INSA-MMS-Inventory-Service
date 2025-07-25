package com.MMS.Inventory_Information.dto.request;


import com.MMS.Inventory_Information.enums.DisposalStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class FixedAssetDisposalRequest {
        private UUID tenantId; // Reference to Tenant from tenant-service
        private UUID storeId; // Reference to Store from store-service
        private UUID processedById; // Reference to Employee from employee-service
        private String fixedAssetDisposalNo; // Unique identifier for the disposal
        private String processedBy; // Optional snapshot of the employee who processed the disposal
        private LocalDate approvedDate; // Date when the disposal was approved
        private DisposalStatus disposalStatus; // e.g. "Pending", "Approved", "Rejected"
        private LocalDate processedOn; // Date when the disposal was processed
        private LocalDate proposedDate;

        private UUID disposableAssetId;

        private List<FixedAssetDisposalDetailRequest> disposalDetails; // List of details related to the disposal


}
