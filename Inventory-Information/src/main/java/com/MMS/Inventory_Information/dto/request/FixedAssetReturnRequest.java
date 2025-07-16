package com.MMS.Inventory_Information.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class FixedAssetReturnRequest {
    private UUID tenantId;
    private UUID departmentId;
    private UUID storeId;
    private UUID processedById;
    private UUID returnedById;
    private String processedBy;
    private String returnedBy;
    private String status;
    private LocalDate receivedDate;
    private LocalDate returnedDate;
    private LocalDate processedOn;

    private List<FixedAssetReturnDetailRequest> returnDetails;
}
