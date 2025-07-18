package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FixedAssetDisposalRepository extends JpaRepository<FixedAssetDisposal, UUID> {
    @Query("SELECT t.DisposalNo FROM FixedAssetDisposal t " +
            "WHERE t.tenantId = :tenantId " +
            "AND EXTRACT(YEAR FROM t.createdAt) = :year " +
            "ORDER BY t.createdAt DESC")
    List<String> findRecentDisposalNumbers(UUID tenantId, int currentYear);

    List<FixedAssetDisposal> findAllByTenantId(UUID tenantId);

    Optional<FixedAssetDisposal> findByTenantIdAndFA_disposalNo(UUID tenantId, String disposalNumber);
}
