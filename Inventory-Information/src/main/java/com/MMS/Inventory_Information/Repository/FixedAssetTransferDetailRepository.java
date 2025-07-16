package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransferDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixedAssetTransferDetailRepository extends JpaRepository<FixedAssetTransferDetail, UUID> {
    void deleteAllByFixedAssetTransferId(UUID id);
    // Additional query methods can be defined here if needed

}
