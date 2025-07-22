package com.MMS.Inventory_Information.model.InventoryCountSheet;

import com.MMS.Inventory_Information.enums.CountType;
import com.MMS.Inventory_Information.enums.StoreType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @Version
    private Integer version=0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;



    private UUID tenantId;

    @Column(nullable = false, unique = true)
    private String inventoryCountNumber;

    @Column(nullable = false)
    private UUID storeId; // From store-service

    private UUID preparedById; // From employee-service

    private UUID committeeId; // FK to committee-service

    private String committeeName; // Name of the committee at the time of count

    @ElementCollection
    @CollectionTable(name = "inventory_count_committee_members", joinColumns = @JoinColumn(name = "inventory_count_id"))
    @Column(name = "member_id")
    private List<UUID> committeeMembersId; // From employee-service

    @ElementCollection
    @CollectionTable(name = "inventory_count_committee_member_names", joinColumns = @JoinColumn(name = "inventory_count_id"))
    @Column(name = "member_name")
    private List<String> committeeMembersName; // Snapshot

    @Enumerated(EnumType.STRING)
    private CountType countType;    // e.g., Periodic, Perpetual

    @Enumerated(EnumType.STRING)
    private StoreType storeType; // e.g., Internal, Merchandise

    private UUID budgetYearId;
    private LocalDate countDate;

    private String preparedBy;
    private LocalDate preparedOn;

    @CreatedDate
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
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "inventoryCount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryDetail> inventoryDetails;
}

