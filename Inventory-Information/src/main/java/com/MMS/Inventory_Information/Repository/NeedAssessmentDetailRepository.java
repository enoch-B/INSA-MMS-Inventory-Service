package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.NeedAssessment.NeedAssessmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NeedAssessmentDetailRepository extends JpaRepository<NeedAssessmentDetail, UUID> {
}
