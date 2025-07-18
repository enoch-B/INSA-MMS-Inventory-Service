package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface InventoryCountRepository extends JpaRepository<InventoryCount, UUID> {

    @Query("SELECT ic.inventoryCountNumber FROM InventoryCount ic " +
            "WHERE ic.tenantId = :tenantId AND FUNCTION('YEAR', ic.createdAt) = :currentYear " +
            "ORDER BY ic.createdAt DESC")
    List<String> findRecentInventoryCountNumbers(@Param("tenantId") UUID tenantId,
                                                 @Param("currentYear") int currentYear);

    Page<InventoryCount> findByTenantId(UUID tenantId, Pageable pageable);

    List<InventoryCount> findAllByTenantId(UUID tenantId);
}
