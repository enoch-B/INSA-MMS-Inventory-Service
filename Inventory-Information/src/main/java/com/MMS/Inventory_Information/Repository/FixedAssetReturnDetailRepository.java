package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetReturn.FixedAssetReturnDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixedAssetReturnDetailRepository extends JpaRepository<FixedAssetReturnDetail, UUID> {

}
