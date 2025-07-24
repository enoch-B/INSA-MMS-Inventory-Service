package com.MMS.Inventory_Information.Repository;

import com.MMS.Inventory_Information.model.FixedAssetReturn.FixedAssetReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixedAssetReturnRepository extends JpaRepository<FixedAssetReturn, UUID> {

    Page<FixedAssetReturn> findByTenantId(UUID tenantId, Pageable pageable);
}
