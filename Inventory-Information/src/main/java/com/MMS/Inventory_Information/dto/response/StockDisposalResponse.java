package com.MMS.Inventory_Information.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class StockDisposalResponse {
    private UUID id;
    private UUID tenantId;
    private UUID storeId;
    private UUID processedById;
    private String processedBy;
    private String disposalNo;
    private String disposalStatus;
    private LocalDate proposeDate;
    private LocalDate approvedDate;
    private LocalDate proposedOn;
    private String fileName;
    private String fileType;
    private Byte[] fileData;

    private List<StockDisposalDetailResponse> stockDisposalDetails;
}
