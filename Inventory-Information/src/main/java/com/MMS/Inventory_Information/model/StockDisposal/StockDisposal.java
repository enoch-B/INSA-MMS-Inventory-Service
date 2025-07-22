package com.MMS.Inventory_Information.model.StockDisposal;

import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;
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
@Table(name = "stock_disposal")
public class StockDisposal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID tenantId;

    private UUID storeId;
    private UUID processedById;
    private String processedBy;

    @Column(nullable = false)
    private String disposalNo;

    @Column(nullable = false)
    private String disposalStatus;

    @Column(nullable = false)
    private LocalDate proposeDate;

    @Column(nullable = false)
    private LocalDate approvedDate;

    @Column(nullable = false)
    private LocalDate proposedOn;


    private String fileName;
    private String fileType;
    private byte[] fileData;


    //Auditing fields
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

    @OneToMany(mappedBy = "StockDisposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockDisposalDetail> stockDisposalDetails; // Details of the disposed assets


}
