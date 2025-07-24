package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.FixedAssetReturnMapper;
import com.MMS.Inventory_Information.Repository.FixedAssetReturnDetailRepository;
import com.MMS.Inventory_Information.Repository.FixedAssetReturnRepository;
import com.MMS.Inventory_Information.dto.request.FixedAssetReturnRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetReturnResponse;
import com.MMS.Inventory_Information.model.FixedAssetReturn.FixedAssetReturn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<FixedAssetReturnResponse> getAllFixedAssetReturns(UUID tenantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return fixedAssetReturnRepository.findByTenantId(tenantId, pageable)
                .map(FixedAssetReturnMapper::toResponse);
    }

    public FixedAssetReturnResponse getFixedAssetReturnById(UUID tenantId, UUID id) {
        FixedAssetReturn fixedAssetReturn = fixedAssetReturnRepository.findById(id)
                .filter(far -> far.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Return Not Found or Tenant Mismatch"));
        return FixedAssetReturnMapper.toResponse(fixedAssetReturn);
    }

    public void deleteFixedAssetReturn(UUID tenantId, UUID id) {
        FixedAssetReturn fixedAssetReturn = fixedAssetReturnRepository.findById(id)
                .filter(far -> far.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Return Not Found or Tenant Mismatch"));

        fixedAssetReturnRepository.delete(fixedAssetReturn);

    }

    public FixedAssetReturnResponse updateFixedAssetReturn(UUID tenantId, UUID id, FixedAssetReturnRequest request) {
        FixedAssetReturn fixedAssetReturn = fixedAssetReturnRepository.findById(id)
                .filter(far -> far.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Return Not Found or Tenant Mismatch"));
        // Update fields from request


        // Save updated entity
        FixedAssetReturn updatedEntity = fixedAssetReturnRepository.save(fixedAssetReturn);

        // Convert to response
        return FixedAssetReturnMapper.toResponse(updatedEntity);
    }
}
