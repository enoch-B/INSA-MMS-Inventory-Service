package com.MMS.Inventory_Information.model.StockDisposal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "stock_disposal_detail")
public class StockDisposalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID itemsId; // from item-service
    private UUID processedBy; // from user-service

    @Column(nullable = false)
    private String disposalMethod; // e.g., "Sell", "Donate", "Recycle", etc.

    @Column(nullable = false)
    private  String description; // Description of the disposal action

    @Column(nullable = false)
    private BigDecimal sellingPrice; // Price at which the item was disposed of, if applicable


    @Column(nullable = false)
    private LocalDateTime expirationDate; // Date when the item was disposed of

   @Column(nullable=false)
    private LocalDateTime processedOn; // Date when the disposal was processed

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_disposal_id", nullable = false)
    private StockDisposal stockDisposal; // Reference to the parent StockDisposal entity


}
