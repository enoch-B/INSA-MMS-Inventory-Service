package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.LostStockItem.LostStockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LostStockItemRepository extends JpaRepository<LostStockItem, UUID> {

}
