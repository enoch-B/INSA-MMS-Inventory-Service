package com.MMS.Inventory_Information.model.InventoryCountSheet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "inventory_details")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventoryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID tenantId;

    @Column(nullable = false)
    private Long itemId;  // From item-service

    private String itemName;    // Optional: store snapshot
    private String itemCode;    // Optional: store snapshot
    private String unitMeasure;

    private int quantity;
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_count_id")
    private InventoryCount inventoryCount;
}
