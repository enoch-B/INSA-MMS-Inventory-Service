package com.MMS.Inventory_Information.dto.request;


import com.MMS.Inventory_Information.enums.TransferType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FixedAssetTransferRequest {
       private UUID tenantId;
        private String transferNo;
        private UUID employeeId;
        private UUID departmentId;
        private UUID transferToId;
        private UUID transferFromId;
        private String processedBy;         // Optional snapshot
        private UUID preparedById; // FK to employee-service
        private LocalDate processedOn;
        private TransferType transferType; // Enum for transfer type (e.g., INTERNAL, EXTERNAL, etc.)
    private List<FixedAssetTransferDetailRequest> transferDetails;
}
