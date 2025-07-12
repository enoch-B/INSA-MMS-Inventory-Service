package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalanceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryBalanceItemRepository extends JpaRepository<InventoryBalanceItem, UUID> {
}
