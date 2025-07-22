package com.MMS.Inventory_Information.model.LostStockItem;

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
@Table(name = "lost_stock_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostStockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID tenantId;

    private String lostStockItemNo;

    private String status;

    private LocalDate registrationDate;

    private UUID regionId;       // FK to region-service (or static lookup)
    private UUID departmentId;   // FK to HR/department service
    private UUID storeId;        // FK to store-service

    private UUID committeeId; // FK to committee-service

    private String committeeName; // Name of the committee at the time of count

    @ElementCollection
    @CollectionTable(
            name = "lost_stock_committee_members", // ✅ Unique table name
            joinColumns = @JoinColumn(name = "lost_stock_item_id") // ✅ Unique FK name
    )
    @Column(name = "member_id")
    private List<UUID> committeeMemberIds;

    @ElementCollection
    @CollectionTable(
            name = "lost_stock_committee_member_names", // Unique table name
            joinColumns = @JoinColumn(name = "lost_stock_item_id")
    )
    @Column(name = "member_name")
    private List<String> committeeMembersName;


    private UUID processedById;      // FK to user/employee-service

    private String processedBy;      // Optional snapshot of user ID
    private LocalDate processedOn;

    // Relations
    @OneToMany(mappedBy = "lostStockItem", cascade = CascadeType.ALL)
    private List<LostStockItemDetail> itemDetails;

    // File attachment
    private String fileName;
    private String fileType;

    @Lob
    @Column(name="data")
    private byte[] data; // File data as a byte array

       // audit fields
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


}

