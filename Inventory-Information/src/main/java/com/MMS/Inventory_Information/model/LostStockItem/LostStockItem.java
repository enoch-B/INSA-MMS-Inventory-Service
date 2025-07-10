package com.MMS.Inventory_Information.model.LostStockItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID tenantId;

    private String lostStockItemNo;

    private String status;

    private LocalDate registrationDate;

    private Long regionId;       // FK to region-service (or static lookup)
    private Long departmentId;   // FK to HR/department service
    private Long storeId;        // FK to store-service

//    private UUID committeeId;        // FK to committee-service

//    @Column(nullable = false)
    private String committeeName; // Optional snapshot of committee name
    private String committeeMembers; // Optional snapshot of committee members

    private Long processedById;      // FK to user/employee-service
    private LocalDate processedOn;

    // Relations
    @OneToMany(mappedBy = "lostStockItem", cascade = CascadeType.ALL)
    private List<LostStockItemDetail> itemDetails;

    @ElementCollection
    private List<Long> committeeMemberIds; // IDs of users (FK to employee-service)

    // File attachment
    private String fileUrl;
    private String fileName;
    private String fileType;

    @ElementCollection
    private List<String> file;

    // Audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

