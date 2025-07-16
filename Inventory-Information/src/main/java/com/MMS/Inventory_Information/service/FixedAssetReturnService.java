package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.FixedAssetReturnMapper;
import com.MMS.Inventory_Information.Repository.FixedAssetReturnDetailRepository;
import com.MMS.Inventory_Information.Repository.FixedAssetReturnRepository;
import com.MMS.Inventory_Information.dto.request.FixedAssetReturnRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetReturnResponse;
import com.MMS.Inventory_Information.model.FixedAssetReturn.FixedAssetReturn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FixedAssetReturnService {

    private final FixedAssetReturnRepository fixedAssetReturnRepository;
    private final FixedAssetReturnDetailRepository fixedAssetReturnDetailRepository;

    public FixedAssetReturnResponse addFixedAssetReturn(UUID tenantId, FixedAssetReturnRequest request) {
        // Step 1: Set tenantId from URL into request body
        request.setTenantId(tenantId);

        // Step 2: Convert request → entity
        FixedAssetReturn entity = FixedAssetReturnMapper.toEntity(request);

        // Step 3: Save entity (JPA automatically cascades details)
        FixedAssetReturn savedEntity = fixedAssetReturnRepository.save(entity);

        // Step 4: Convert saved entity → response
        return FixedAssetReturnMapper.toResponse(savedEntity);
    }
}
