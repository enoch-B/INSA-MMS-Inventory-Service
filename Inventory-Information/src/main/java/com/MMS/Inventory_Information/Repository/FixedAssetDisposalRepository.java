package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixedAssetDisposalRepository extends JpaRepository<FixedAssetDisposal, UUID> {
}
