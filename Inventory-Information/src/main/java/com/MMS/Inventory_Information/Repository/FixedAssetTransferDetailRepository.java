package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransferDetail;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FixedAssetTransferDetailRepository extends JpaRepository<FixedAssetTransferDetail, UUID> {

    // Additional query methods can be defined here if needed
    @Modifying
    @Query("DELETE FROM FixedAssetTransferDetail d WHERE d.fixedAssetTransfer.id = :transferId")
    void deleteAllByFixedAssetTransferId(@Param("transferId") UUID transferId);

}
