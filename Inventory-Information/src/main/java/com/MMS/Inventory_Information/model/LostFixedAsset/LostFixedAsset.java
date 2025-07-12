package com.MMS.Inventory_Information.model.LostFixedAsset;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "lost_fixed_assets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LostFixedAsset {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @Column(nullable = false)
        private UUID tenantId;

        @Column(nullable = false, unique = true)
        private String lostItemNo;

        private UUID storeId;        // from store-service
        private UUID   departmentId;   // from org-service
            // optional snapshot

        private UUID  processedById; // from employee-service


        @Column(nullable = false)
        private LocalDate registrationDate;

        private String processedBy;  // optional snapshot
        private LocalDate processedOn;

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

        @OneToMany(mappedBy = "lostFixedAsset", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<LostItemDetail> lostItemDetails;

        @OneToMany(mappedBy = "lostFixedAsset", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<LostAssetFile> attachments;


}
