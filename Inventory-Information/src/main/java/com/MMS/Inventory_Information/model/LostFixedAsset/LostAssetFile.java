package com.MMS.Inventory_Information.model.LostFixedAsset;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "lost_asset_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LostAssetFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID  id;

    private String filename;
    private String fileUrl;
    private String fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_fixed_asset_id")
    private LostFixedAsset lostFixedAsset;
}

