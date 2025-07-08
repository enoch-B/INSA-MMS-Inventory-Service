package com.MMS.Inventory_Information.model.inventoryBalanceSheet;


import com.MMS.Inventory_Information.enums.StoreType;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Table(name = "inventory_balance")
@NoArgsConstructor
@Data
public class InventoryBalance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private UUID id;

        @Column(nullable = false,unique = true)
        private UUID tenantId;

        private Long preparedById; // FK to employee-service
        // FK to Count Sheet
        @ManyToOne
        @JoinColumn(name = "inventory_count_id", nullable = false)
        private InventoryCount inventoryCount;

        private LocalDate budgetYear;


        private StoreType storeType;


        private String preparedBy;  // Snapshot of user ID
        private LocalDate preparedOn;

        @OneToMany(mappedBy = "inventoryBalance", cascade = CascadeType.ALL)
        private List<InventoryBalanceItem> items;


}
