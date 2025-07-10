package com.MMS.Inventory_Information.model.InventoryCountSheet;

import com.MMS.Inventory_Information.enums.CountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inventory_counts")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class InventoryCount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private UUID tenantId;

    @Column(nullable = false, unique = true)
    private String inventoryCountNumber;

    @Column(nullable = false)
    private UUID storeId; // From store-service
    private UUID preparedById; // From user-service
    private String committeeId;

    @Enumerated(EnumType.STRING)
    private CountType countType;    // e.g., Periodic, Perpetual

    private String budgetYear;
    private LocalDate countDate;

    private String preparedBy; //Optional snapshot of user ID
    private LocalDate preparedOn;

//    private String committeeId;
//    private String committeeMembers;


    private LocalDateTime createdAt;
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


    @OneToMany(mappedBy = "inventoryCount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryDetail> inventoryDetails;



}
