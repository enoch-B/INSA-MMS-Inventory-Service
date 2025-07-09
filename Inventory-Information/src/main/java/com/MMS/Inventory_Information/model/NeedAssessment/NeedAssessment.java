package com.MMS.Inventory_Information.model.NeedAssessment;

import com.MMS.Inventory_Information.enums.PurchaseType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public class NeedAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID tenantId;

    @Enumerated(EnumType.STRING)
    private PurchaseType purchaseType; // GOODS, SERVICE, WORK

    private UUID departmentId; // from department-service
    private UUID storeId; // from store-service
    private String budgetYear;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "needAssessment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NeedAssessmentDetail> items;
}
