package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.LostFixedAsset.LostItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LostItemDetailRepository extends JpaRepository<LostItemDetail, UUID> {
    // Additional query methods can be defined here if needed
}
