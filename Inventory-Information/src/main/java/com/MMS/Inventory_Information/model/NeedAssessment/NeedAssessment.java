package com.MMS.Inventory_Information.model.NeedAssessment;

import com.MMS.Inventory_Information.enums.PurchaseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "need_assessment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeedAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID tenantId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseType purchaseType; // GOODS, SERVICE, WORK

    private UUID departmentId; // from department-service
    private UUID storeId; // from store-service
    private UUID budgetYearId;


    //Audit fields
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



    @OneToMany(mappedBy = "needAssessment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NeedAssessmentDetail> needAssessmentDetails;
}
