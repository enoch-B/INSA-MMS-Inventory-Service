package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixedAssetDisposalDetailRepository extends JpaRepository<FixedAssetDisposalDetail, UUID> {
}
