package com.MMS.Inventory_Information.model.FixedAssetDisposal;

import com.MMS.Inventory_Information.enums.DisposalStatus;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableAsset;
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
@Table(name = "fixed_asset_disposal")
public class FixedAssetDisposal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

     @Column(nullable = false)
    private UUID tenantId;

    private UUID storeId; // from store-service
    private UUID processedById; // from employee-service

    private String processedBy;

    @Column(nullable = false)
    private String fixedAssetDisposalNo;

    @Column(nullable = false)
    private LocalDate approvedDate;

    @Column(nullable = false)
    private LocalDate processedOn;

    @Column(nullable = false, updatable = false)
    private LocalDate proposedDate;

    private String fileName;
    private String fileType;
    private byte[] fileData;

    @CreatedDate
    @Column(nullable = false, updatable = false)
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

    // Join with DisposableAsset
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disposable_asset_id", nullable = false)
    private DisposableAsset disposableAsset;

    @OneToMany(mappedBy = "fixedAssetDisposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FixedAssetDisposalDetail> disposalDetails;
}
