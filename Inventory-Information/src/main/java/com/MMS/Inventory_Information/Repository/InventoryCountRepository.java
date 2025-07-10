package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryCountRepository extends JpaRepository<InventoryCount, UUID> {
    // Additional query methods can be defined here if needed
}
