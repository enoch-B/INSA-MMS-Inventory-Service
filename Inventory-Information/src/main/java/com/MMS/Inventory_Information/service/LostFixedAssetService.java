package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Mapper.LostFixedAssetMapper;
import com.MMS.Inventory_Information.Repository.LostFixedAssetRepository;
import com.MMS.Inventory_Information.Repository.LostItemDetailRepository;
import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.dto.response.LostFixedAssetResponse;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostItemDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LostFixedAssetService {

    private final LostFixedAssetRepository lostFixedAssetRepository;
    private final LostItemDetailRepository lostItemDetailRepository;

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

    public List<LostFixedAssetResponse> getAllLostFixedAssets(UUID tenantId) {
        // Fetch only the LostFixedAsset entities for the given tenantId (filtered in DB query)
        List<LostFixedAsset> lostFixedAssets = lostFixedAssetRepository.findByTenantId(tenantId);

        // Map entities to DTO responses
        return lostFixedAssets.stream()
                .map(LostFixedAssetMapper::toResponse)
                .collect(Collectors.toList());
    }
}
