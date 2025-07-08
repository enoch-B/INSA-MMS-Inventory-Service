package com.MMS.Inventory_Information.model.InventoryCountSheet;

import com.MMS.Inventory_Information.enums.CountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inventory_counts")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class InventoryCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private UUID tenantId;

    @Column(nullable = false, unique = true)
    private String inventoryCountNumber;

    @Column(nullable = false)
    private Long storeId; // From store-service
    private Long preparedById; // From user-service
    private CountType countType;    // e.g., Periodic, Perpetual
    private String budgetYear;
    private LocalDate countDate;

    private String preparedBy; //snapshot of user ID
    private LocalDate preparedOn;

    private String committee;
    private String committeeMembers;

    @OneToMany(mappedBy = "inventoryCount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryDetail> inventoryDetails;



}
