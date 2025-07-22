package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.DisposalCollection.DisposableFixedAssetDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DisposableFixedAssetDetailRepository extends JpaRepository<DisposableFixedAssetDetail, UUID> {
}
