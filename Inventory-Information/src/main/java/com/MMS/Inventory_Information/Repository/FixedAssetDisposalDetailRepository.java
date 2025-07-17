package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposal;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FixedAssetDisposalDetailRepository extends JpaRepository<FixedAssetDisposalDetail, UUID> {
    @Modifying
    @Query("DELETE FROM FixedAssetDisposalDetail d WHERE d.fixedAssetDisposal.id = :disposalId")
    void deleteAllByFixedAssetDisposalId(@Param("disposalId") UUID disposalId);
}
