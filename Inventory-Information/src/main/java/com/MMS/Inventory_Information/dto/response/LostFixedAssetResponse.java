package com.MMS.Inventory_Information.dto.response;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class LostFixedAssetResponse {
    private UUID id;
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

    private LocalDate createdAt;
    private LocalDate updatedAt;

    private List<LostItemDetailResponse> lostFixedAssetDetails;


}
