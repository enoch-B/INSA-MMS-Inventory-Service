package com.MMS.Inventory_Information.model.LostStockItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostStockItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "lost_stock_item_id")
    private LostStockItem lostStockItem;

    private UUID itemId;        // FK to item-service

//    // Snapshot fields (optional but recommended for audit)
//    private String itemName;
//    private String itemCode;
//    private BigDecimal unitPrice;

    private Integer Quantity;

    private String description;
    private String duration;
    private String remark;
}

