package com.MMS.Inventory_Information.model.FixedAssetDisposal;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "fixed_asset_disposal_details")
public class FixedAssetDisposalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID itemsId; // from asset-service

    private UUID fixedAssetId; // from fixed-asset-service

    @Column(nullable = false)
    private String itemLocation;

    @Column(nullable = false)
    private BigDecimal sellingPrice;
    @Column(nullable = false)
    private BigDecimal gainLossValue;

    @Column(nullable = false)
    private String disposalMethod; // e.g., "Sale", "Donation", "Scrap"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fixed_asset_disposal_id", nullable = false)
    private FixedAssetDisposal fixedAssetDisposal;

}
