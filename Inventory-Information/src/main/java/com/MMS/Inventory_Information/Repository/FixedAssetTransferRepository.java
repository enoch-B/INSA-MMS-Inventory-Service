package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FixedAssetTransferRepository extends JpaRepository<FixedAssetTransfer, UUID> {
    @Query("SELECT t.transferNo FROM FixedAssetTransfer t " +
            "WHERE t.tenantId = :tenantId " +
            "AND EXTRACT(YEAR FROM t.createdAt) = :year " +
            "ORDER BY t.createdAt DESC")

    List<String> findRecentTransferNumbers(UUID tenantId, int currentYear);
    List<FixedAssetTransfer> findByTenantId(UUID tenantId);
    Optional<FixedAssetTransfer> findByTenantIdAndTransferNo(UUID tenantId, String transferNumber);
}
