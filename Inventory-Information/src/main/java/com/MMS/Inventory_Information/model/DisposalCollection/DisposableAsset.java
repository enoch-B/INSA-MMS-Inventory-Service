package com.MMS.Inventory_Information.model.DisposalCollection;

import com.MMS.Inventory_Information.enums.DisposableType;
import com.MMS.Inventory_Information.enums.DisposalStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "disposable_assets")

public class DisposableAsset {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private UUID tenantId;
        private String drNo;

        private UUID storeId;
        private UUID departmentId;

        @Enumerated(EnumType.STRING)
        private DisposableType disposableType; // FIXED_ASSET, NON_FIXED_ASSET, MERCHANDISED

        private LocalDate requisitionDate;

        @Enumerated(EnumType.STRING)
        private DisposalStatus disposalStatus; // PENDING, COMPLETED, etc.

        private UUID processedById;

        private String processedByName; // snapshot
        private LocalDate processedOn;

        @OneToMany(mappedBy = "disposableAsset", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<DisposableFixedAssetDetail> items;


}
