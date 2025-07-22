package com.MMS.Inventory_Information.dto.response;

import com.MMS.Inventory_Information.enums.DisposableType;
import com.MMS.Inventory_Information.enums.DisposalStatus;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableFixedAssetDetail;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class DisposableAssetResponse {
    private UUID id;
    private UUID tenantId;
    private String drNo;
    private UUID storeId;
    private UUID departmentId;
    private UUID processedById;
    private DisposableType disposableType;
    private LocalDate requisitionDate;
    private DisposalStatus disposalStatus;
    private String processedByName;
    private LocalDate processedOn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<DisposableFixedAssetDetailResponse> disposableFixedAssetDetails;

}
