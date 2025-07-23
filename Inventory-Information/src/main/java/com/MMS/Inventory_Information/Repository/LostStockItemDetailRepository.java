package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.LostStockItem.LostStockItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LostStockItemDetailRepository extends JpaRepository<LostStockItemDetail, UUID> {
}
