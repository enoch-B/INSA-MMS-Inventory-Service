package com.MMS.Inventory_Information.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor


public class LostFixedAssetRequest {
    private UUID tenantId;
    private String lostItemNo;
    private UUID storeId;
    private UUID departmentId;
    private UUID processedById;

    private LocalDate registrationDate;
    private String processedBy;         // Optional snapshot
    private LocalDate processedOn;

    private String fileName;
    private String fileType;
    private byte[] data;

    private List<LostItemDetailRequest> lostItemDetails;

}
