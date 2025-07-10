package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LostFARepository extends JpaRepository<LostFixedAsset, UUID> {
}
