package com.MMS.Inventory_Information.model.inventoryBalanceSheet;


import com.MMS.Inventory_Information.enums.StoreType;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Table(name = "inventory_balance")
@NoArgsConstructor
@Data
public class InventoryBalance {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;


        private UUID tenantId;

        private UUID preparedById; // FK to employee-service
        // FK to Count Sheet
        @ManyToOne
        @JoinColumn(name = "inventory_count_id", nullable = false)
        private InventoryCount inventoryCount;


       @Enumerated(EnumType.STRING)
        private StoreType storeType;


        private String preparedBy;  // optional Snapshot of user ID
        private LocalDate preparedOn;


         // audit fields
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

        @OneToMany(mappedBy = "inventoryBalance", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<InventoryBalanceItem> inventoryBalanceItem;


}
