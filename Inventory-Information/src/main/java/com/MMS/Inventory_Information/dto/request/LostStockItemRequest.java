package com.MMS.Inventory_Information.dto.request;

import com.MMS.Inventory_Information.model.LostStockItem.LostStockItemDetail;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class LostStockItemRequest {
    private UUID tenantId;

    private String lostStockItemNo;

    private String status;

    private LocalDate registrationDate;

    private UUID regionId;
    private UUID departmentId;
    private UUID storeId;

    private UUID committeeId; // FK to committee-service
    private String committeeName; // Name of the committee at the time of count
    private List<UUID> committeeMembersId;
    private List<String> committeeMembersName;

    private UUID processedById;      // FK to user/employee-service

    private String processedBy;      // Optional snapshot of user ID
    private LocalDate processedOn;

    private String fileName;
    private String fileType;
    private byte[] data;

    private List<LostStockItemDetailRequest>  lostStockItemDetailRequest;




}
