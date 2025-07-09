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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private UUID tenantId;

        @Column(nullable = false)
        private String transferNo;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private TransferType transferType;

        @Column(nullable = false)
        private String transferFrom;

        @Column(nullable = false)
        private String transferTo;

        private String transferDepartment;

        private String processedBy;
        private LocalDate processedOn;

        @OneToMany(mappedBy = "fixedAssetTransfer", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<FixedAssetTransferDetail> transferDetails;


}
