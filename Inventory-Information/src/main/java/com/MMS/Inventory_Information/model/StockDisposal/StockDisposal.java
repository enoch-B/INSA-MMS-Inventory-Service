package com.MMS.Inventory_Information.model.StockDisposal;

import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "stock_disposal")
public class StockDisposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID tenantId;

    private UUID storeId;
    private UUID processedById;

    @Column(nullable = false)
    private String disposalNo;

    @Column(nullable = false)
    private String disposalStatus;

    @Column(nullable = false)
    private LocalDateTime proposeDate;

    @Column(nullable = false)
    private String approvedDate;

    @Column(nullable = false)
    private LocalDateTime proposedOn;

    @Column(nullable = false, updatable = false)
    private String attachmentUrl;

    @OneToMany(mappedBy = "StockDisposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockDisposalDetail> stockDisposalDetails; // Details of the disposed assets


}
