package com.MMS.Inventory_Information.model.LostStockItem;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class LostStockItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lost_stock_item_id")
    private LostStockItem lostStockItem;

    private Long itemId;        // FK to item-service

    // Snapshot fields (optional but recommended for audit)
    private String itemName;
    private String itemCode;
    private BigDecimal unitPrice;

    private Integer itemQuantity;

    private String description;
    private String duration;
    private String remark;
}

