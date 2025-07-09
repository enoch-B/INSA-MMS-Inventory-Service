package com.MMS.Inventory_Information.model.DisposalCollection;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "disposable_asset_detail")
@Data
public class DisposableFixedAssetDetail {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "disposable_asset_id")
        private DisposableAsset disposableFixedAsset;

        private UUID fixedAssetId; // From fixed-asset-service
        private String tagNumber;  // From fixed-asset-service

        private int quantity;
        private String itemCode;   // snapshot
        private String itemName;   // snapshot
       private BigDecimal unitPrice; //Snapshot;

        private BigDecimal bookValueSnapshot;

        private String accountCodeSnapshot;
        private String itemStatus; // optional snapshot

        private String description;

        private LocalDate expirationDate;
        private String batchNo;


}
