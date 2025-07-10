package com.MMS.Inventory_Information.model.FixedAssetTransfer;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "fixed_asset_transfer_details")

public class FixedAssetTransferDetail {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private UUID fixedAssetId; // Reference to Fixed Asset from fixed-asset-service
        private UUID itemId; // Reference to Item from item-service
        private UUID tagId; // Reference to Tag from fixed-asset-service

//        private String tagNo;    optional snapshots
//        private String itemName;
//        private String unitMeasure;
//        private Double unitPrice;


        private Double bookValue;
        private String accountCode;

        @Column(nullable = false)
        private Integer quantity;


        @Column(nullable = false)
        private String remark;

        @Column(nullable = false)
        private String description;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "fixed_asset_transfer_id")
        private FixedAssetTransfer fixedAssetTransfer;


}
