package com.MMS.Inventory_Information.model.LostStockItem;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class LostStockItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lost_stock_item_id")
    private LostStockItem lostStockItem;

    private UUID itemsId;        // FK to item-service

    // Snapshot fields (optional but recommended for audit)
    private String itemName;
    private String itemCode;
    private BigDecimal unitPrice;

    private Integer itemQuantity;

    private String description;
    private String duration;
    private String remark;
}

