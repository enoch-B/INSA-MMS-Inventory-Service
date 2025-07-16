package com.MMS.Inventory_Information.dto.response;


import com.MMS.Inventory_Information.enums.TransferType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class FixedAssetTransferResponse {
    private UUID id;
    private UUID tenantId;
    private UUID employeeId;
    private UUID departmentId;
    private UUID transferToId;
    private UUID transferFromId;
    private String transferNo;
    private String processedBy;         // Optional snapshot
    private UUID preparedById; // FK to employee-service
    private LocalDate processedOn;
    private TransferType transferType; // Enum for transfer type (e.g., INTERNAL, EXTERNAL, etc.)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<FixedAssetTransferDetailResponse> transferDetails;
}
