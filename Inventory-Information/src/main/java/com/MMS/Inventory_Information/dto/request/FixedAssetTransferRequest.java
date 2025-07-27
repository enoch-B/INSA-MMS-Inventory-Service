package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.enums.TransferType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FixedAssetTransferRequest {

        @NotNull(message = "Tenant ID is required")
        private UUID tenantId;

        @NotBlank(message = "Transfer number is required")
        private String transferNo;

        @NotNull(message = "Employee ID is required")
        private UUID employeeId;

        @NotNull(message = "Department ID is required")
        private UUID departmentId;

        @NotNull(message = "Transfer To ID is required")
        private UUID transferToId;

        @NotNull(message = "Transfer From ID is required")
        private UUID transferFromId;

        private String processedBy; // Optional snapshot

        @NotNull(message = "Prepared By ID is required")
        private UUID preparedById;

        @NotNull(message = "Processed on date is required")
        private LocalDate processedOn;

        @NotNull(message = "Transfer type is required")
        private TransferType transferType;

        @NotNull(message = "Transfer details must not be null")
        @Size(min = 1, message = "At least one transfer detail is required")
        @Valid
        private List<FixedAssetTransferDetailRequest> transferDetails;
}
