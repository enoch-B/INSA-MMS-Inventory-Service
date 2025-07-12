package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryCountDetailRepository extends JpaRepository<InventoryDetail, UUID> {
}
