package com.MMS.Inventory_Information.model.FixedAssetDisposal;

import com.MMS.Inventory_Information.enums.DisposalStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "fixed_asset_disposal")
public class FixedAssetDisposal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID tenantId;
    private UUID storeId;        // from store-service
    private UUID processedById; // from employee-service

    private String processedBy;  // optional snapshot
//    private String storeName; // optional snapshot of the store name

    @Column(nullable = false)
    private LocalDate approvedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DisposalStatus disposalStatus; // e.g. "Pending", "Approved", "Rejected"

    @Column(nullable = false)
    private LocalDate processedOn; // Date when the disposal was processed

    @Column(nullable = false)
    private String fixedAssetDisposalNo;


    @Column(nullable = false, updatable = false)
    private LocalDate proposedDate;

    @Column(nullable = true)

    private String fileName; // Name of the attached file
    private String fileType; // Type of the attached file (e.g., PDF, DOCX)
    private byte[] fileData; // Binary data of the attached file

    // Auditing fields
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
        this.updatedAt = LocalDateTime.now(); // Only update timestamp
    }


    @OneToMany(mappedBy = "fixedAssetDisposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<FixedAssetDisposalDetail> disposalDetails; // Details of the disposed assets



}