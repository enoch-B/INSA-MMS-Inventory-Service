package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface InventoryCountRepository extends JpaRepository<InventoryCount, UUID> {
    // Additional query methods can be defined here if needed
    @Query("SELECT ic.inventoryCountNumber FROM InventoryCount ic " +
            "WHERE ic.tenantId = :tenantId AND FUNCTION('YEAR', ic.createdAt) = :currentYear " +
            "ORDER BY ic.createdAt DESC")
    List<String> findRecentInventoryCountNumbers(@Param("tenantId") UUID tenantId,
                                                 @Param("currentYear") int currentYear);

}
