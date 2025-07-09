package com.MMS.Inventory_Information.model.LostFixedAsset;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lost_item_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LostItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assetTagId;      // from asset-service
    private String tagNo;
    private String itemName;      // optional snapshot
    private String description;
    private String remark;

    private String duration;      // as string or better → `int` + unit
    private Double bookValue;
    private String accountCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_fixed_asset_id")
    private LostFixedAsset lostFixedAsset;
}
