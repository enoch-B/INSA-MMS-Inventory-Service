package com.MMS.Inventory_Information.model.FixedAssetTransfer;


import com.MMS.Inventory_Information.enums.TransferType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fixed_asset_transfers")
@Data
public class FixedAssetTransfer {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private UUID tenantId;
        private UUID employeeId; // to employee-service
        private UUID departmentId; //  to department-service
        private UUID preparedById; // FK to employee-service

        @Column(nullable = false)
        private String transferNo;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private TransferType transferType;

        @Column(nullable = false)
        private UUID transferFromId;

        @Column(nullable = false)
        private UUID transferToId;

//        private String transferDepartment; // Optional snapshot of department name

//        private String processedBy;
        private LocalDate processedOn;

        @OneToMany(mappedBy = "fixedAssetTransfer", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<FixedAssetTransferDetail> transferDetails;


}
