package com.MMS.Inventory_Information.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private UUID tenantId;

        @Column(nullable = false, unique = true)
        private String lostItemNo;

        private Long storeId;        // from store-service
        private Long departmentId;   // from org-service
        private String region;       // optional snapshot
        private  String employeeId;   // from employee-service
        private Long processedById; // from employee-service
        private LocalDate registrationDate;

        private String processedBy;  // optional snapshot
        private LocalDate processedOn;

        @OneToMany(mappedBy = "lostFixedAsset", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<LostItemDetail> lostItemDetails;

        @OneToMany(mappedBy = "lostFixedAsset", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<LostAssetFile> attachments;


}
