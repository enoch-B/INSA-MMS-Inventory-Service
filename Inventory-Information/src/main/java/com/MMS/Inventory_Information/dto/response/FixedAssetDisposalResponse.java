package com.MMS.Inventory_Information.dto.response;


import com.MMS.Inventory_Information.enums.DisposalStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class FixedAssetDisposalResponse {
    private UUID id;
    private UUID tenantId; // Reference to Tenant from tenant-service
    private UUID storeId; // Reference to Store from store-service
    private UUID processedById; // Reference to Employee from employee-service
    private String fixedAssetDisposalNo; // Unique identifier for the disposal
    private String processedBy; // Optional snapshot of the employee who processed the disposal
    private LocalDate approvedDate; // Date when the disposal was approved
    private DisposalStatus disposalStatus; // e.g. "Pending", "Approved", "Rejected"
    private LocalDate processedOn; // Date when the disposal was processed
    private LocalDate proposedDate;
    private String fileName;
    private String fileType;
    private byte[] fileData; // Binary data of the attached file
    private LocalDate createdAt; // Date when the record was created
    private LocalDate updatedAt; // Date when the record was last updated

    private List<FixedAssetDisposalDetailResponse> disposalDetailResponses; // List of details related to the disposal

}
