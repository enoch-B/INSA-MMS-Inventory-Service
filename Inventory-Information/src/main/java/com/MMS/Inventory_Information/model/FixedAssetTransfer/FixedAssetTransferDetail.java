package com.MMS.Inventory_Information.model.FixedAssetTransfer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "fixed_asset_transfer_details")

public class FixedAssetTransferDetail {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String tagNo;

        private String itemName;

        private String description;

        private String unitMeasure;

        private String remark;

        private Double bookValue;

        private Double unitPrice;

        private Integer quantity;

        private String accountCode;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "fixed_asset_transfer_id")
        private FixedAssetTransfer fixedAssetTransfer;


}
