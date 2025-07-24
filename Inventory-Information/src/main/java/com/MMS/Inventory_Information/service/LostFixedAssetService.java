package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Mapper.LostFixedAssetMapper;
import com.MMS.Inventory_Information.Repository.LostFixedAssetRepository;
import com.MMS.Inventory_Information.Repository.LostFixedItemDetailRepository;
import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.dto.response.LostFixedAssetResponse;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LostFixedAssetService {

    private final LostFixedAssetRepository lostFixedAssetRepository;
    private final LostFixedItemDetailRepository lostFixedItemDetailRepository;

    public String generateLostItemNo(UUID tenantId) {
        int currentYear = LocalDate.now().getYear();
        List<String> recentNumbers = lostFixedAssetRepository.findRecentLostItemNo(tenantId, currentYear);
        int nextNumber = 1;

        if (!recentNumbers.isEmpty()) {
            try {
                String latest = recentNumbers.get(0); // e.g., "DisposalNO-003/2025"
                String numberPart = latest.split("-")[1].split("/")[0]; // "003"
                nextNumber = Integer.parseInt(numberPart) + 1;
            } catch (Exception e) {
                nextNumber = 1;
            }
        }
        return String.format("LostItemNo-%03d/%d", nextNumber, currentYear);
    }

    public LostFixedAssetResponse addLostFixedAsset(UUID tenantId, LostFixedAssetRequest lostFixedAssetRequest) {
        // create a new LostFixedAsset entity from the request
       String LostItemNo =generateLostItemNo(tenantId);
       //Set the Generated LostItemNo and tenantId

        lostFixedAssetRequest.setLostItemNo(LostItemNo);
        lostFixedAssetRequest.setTenantId(tenantId);
        // Map the DTO to Entity
        LostFixedAsset lostFixedAsset = LostFixedAssetMapper.toEntity(lostFixedAssetRequest);

        //save the entity to the repository
        LostFixedAsset savedEntity = lostFixedAssetRepository.save(lostFixedAsset);

        return LostFixedAssetMapper.toResponse(savedEntity);



    }


    public Page<LostFixedAssetResponse> getAllLostFixedAssets(UUID tenantId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());

        return lostFixedAssetRepository.findByTenantId(tenantId,pageable)
                .map(LostFixedAssetMapper::toResponse);
    }

    public LostFixedAssetResponse getLostFixedAssetById(UUID tenantId, UUID id) {
        LostFixedAsset lostFixedAsset= lostFixedAssetRepository.findById(id)
                .filter(la->la.getTenantId().equals(tenantId))
                .orElseThrow(()->new RuntimeException("LostFixed Asset Not Found By this id" + id));

        return LostFixedAssetMapper.toResponse(lostFixedAsset);
    }

    public LostFixedAsset updateLostFixedAsset(UUID tenantId, UUID id, LostFixedAssetRequest request) {
        LostFixedAsset existing = lostFixedAssetRepository.findById(id)
                .filter(la->la.getTenantId().equals(tenantId))
                .orElseThrow(()->new RuntimeException("The Item Not Found By This Id" + id));

        LostFixedAssetMapper.updateLostFixedAsset(existing,request);

        return lostFixedAssetRepository.save(existing);
    }

    public void deleteLostFixedAsset(UUID tenantId, UUID id) {
        LostFixedAsset lostFixedAsset=lostFixedAssetRepository.findById(id)
                .filter(la->la.getTenantId().equals(tenantId))
                .orElseThrow(()-> new RuntimeException("Item Not Found or Tenant Mismatch"));

        lostFixedAssetRepository.delete(lostFixedAsset);
    }
}
