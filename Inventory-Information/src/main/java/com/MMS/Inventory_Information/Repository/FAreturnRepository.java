package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetReturn.FixedAssetReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FAreturnRepository extends JpaRepository<FixedAssetReturn, UUID> {
}
