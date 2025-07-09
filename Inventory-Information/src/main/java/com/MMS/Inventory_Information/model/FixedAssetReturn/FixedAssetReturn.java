package com.MMS.Inventory_Information.model.FixedAssetReturn;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "fixed_asset_return")

public class FixedAssetReturn {
      //fields for FixedAssetReturn
     @Id
     @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
     @Column(nullable = false, unique = true)
     private UUID id; // Unique identifier for the return record

     private UUID tenantId;// Tenant ID for multi-tenancy support

     private UUID departmentIssueId; //  to department issue
     private UUID storeId; //  to store-service
     private UUID processedById; //  to user/employee-service
     private UUID returnedById; //  to user/employee-service for the person returning the asset


    private String processedBy; // snapshot Name of the user who processed the return
    private String returnedBy; // snapshot Name of the user who returned the asset

    @Column(nullable = false)
    private String status; // Status of the return

    private LocalDate receivedDate; // Date when the asset was received
    private LocalDate returnedDate; // Date when the asset was returned
    private LocalDate processedOn; // Date when the return was processed
     private String returnNo;

        @OneToMany(mappedBy = "fixedAssetReturn", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<FixedAssetReturnDetail> returnDetails; // Details of the returned assets

}
