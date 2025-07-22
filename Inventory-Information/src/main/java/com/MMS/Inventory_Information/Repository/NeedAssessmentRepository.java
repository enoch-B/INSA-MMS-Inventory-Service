package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.NeedAssessment.NeedAssessment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NeedAssessmentRepository extends JpaRepository<NeedAssessment, UUID> {
    Page<NeedAssessment> findByTenantId(UUID tenantId, Pageable pageable);
}
