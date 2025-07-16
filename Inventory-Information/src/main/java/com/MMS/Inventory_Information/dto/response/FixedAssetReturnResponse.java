package com.MMS.Inventory_Information.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class FixedAssetReturnResponse {
    private UUID id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<FixedAssetReturnDetailResponse> returnDetails;
}
