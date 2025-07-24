package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.StockDisposal.StockDisposal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StockDisposalRepository extends JpaRepository<StockDisposal, UUID> {
    @Query("SELECT s.disposalNo FROM StockDisposal s " +
            "WHERE s.tenantId = :tenantId " +
            "AND EXTRACT(YEAR FROM s.createdAt) = :year " +
            "ORDER BY s.createdAt DESC")

    List<String> findRecentDisposalNo(UUID tenantId, int currentYear);

    Page<StockDisposal> findByTenantId(UUID tenantId, Pageable pageable);
}
