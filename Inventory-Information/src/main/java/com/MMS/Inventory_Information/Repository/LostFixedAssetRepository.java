package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LostFixedAssetRepository extends JpaRepository<LostFixedAsset, UUID> {
    @Query("SELECT t.lostItemNo FROM LostFixedAsset t " +
            "WHERE t.tenantId = :tenantId " +
            "AND EXTRACT(YEAR FROM t.createdAt) = :year " +
            "ORDER BY t.createdAt DESC")
    List<String> findRecentLostItemNo(UUID tenantId, int currentYear);

    Page<LostFixedAsset> findByTenantId(UUID tenantId, Pageable pageable);
}
