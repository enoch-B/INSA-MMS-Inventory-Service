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
        private DisposableAsset disposableAsset;

        private UUID fixedAssetId; // From fixed-asset-service
        private UUID itemId; // From item-service
        private String tagNumber;

        private BigDecimal bookValue;
        private String accountCode;


        private String description;
        private Integer quantity;
        private LocalDate expirationDate;
        private String batchNo;


}
