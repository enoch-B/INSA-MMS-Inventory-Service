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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID  id;

    private String filename;
    private String fileType;

    @Lob
    @Column(name="data")
    private byte[] data; // File data as byte array

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lost_fixed_asset_id")
    private LostFixedAsset lostFixedAsset;
}

