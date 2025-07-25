package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryBalanceRepository extends JpaRepository<InventoryBalance, UUID> {
    Page<InventoryBalance> findByTenantId(UUID tenantId, Pageable pageable);
}
