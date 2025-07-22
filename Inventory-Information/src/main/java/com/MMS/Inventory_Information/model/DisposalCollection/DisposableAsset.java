package com.MMS.Inventory_Information.model.DisposalCollection;

import com.MMS.Inventory_Information.enums.DisposableType;
import com.MMS.Inventory_Information.enums.DisposalStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        @CreatedDate
        private LocalDateTime createdAt;

        @LastModifiedDate
        private LocalDateTime updatedAt;

        @PrePersist
        protected void onCreate() {
                this.createdAt = LocalDateTime.now();
                this.updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
                this.updatedAt = LocalDateTime.now();
        }

        @OneToMany(mappedBy = "disposableAsset", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<DisposableFixedAssetDetail> disposableAssetDetail;


}
