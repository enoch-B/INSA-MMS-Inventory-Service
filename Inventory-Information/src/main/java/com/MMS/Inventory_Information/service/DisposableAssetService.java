package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.DisposableAssetMapper;
import com.MMS.Inventory_Information.Repository.DisposableAssetRepository;
import com.MMS.Inventory_Information.Repository.DisposableFixedAssetDetailRepository;
import com.MMS.Inventory_Information.dto.request.DisposableAssetRequest;
import com.MMS.Inventory_Information.dto.response.DisposableAssetResponse;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableAsset;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableFixedAssetDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisposableAssetService {

    private final DisposableAssetRepository disposableAssetRepository;
    private final DisposableFixedAssetDetailRepository disposableFixedAssetDetailRepository;


    /**
     * Generates a unique FA Disposal number per tenant per year.
     */
    public String generateFADNumber(UUID tenantId) {
        int currentYear = LocalDate.now().getYear();
        List<String> recentNumbers = disposableAssetRepository.findRecentFADNumbers(tenantId, currentYear);
        int nextNumber = 1;

        if (!recentNumbers.isEmpty()) {
            try {
                String latest = recentNumbers.get(0); // e.g., "InventoryNO-003/2025"
                String numberPart = latest.split("-")[1].split("/")[0]; // "003"
                nextNumber = Integer.parseInt(numberPart) + 1;
            } catch (Exception e) {
                nextNumber = 1;
            }
        }

        return String.format("FADisposalNo-%03d/%d", nextNumber, currentYear);
    }

    /**
     * Creates and saves a Disposable Asset with its associated detail items.
     *
     *
     */
    public DisposableAssetResponse addDisposalCollection(UUID tenantId, DisposableAssetRequest disposableAssetRequest) {
        String drNumber=generateFADNumber(tenantId);

        disposableAssetRequest.setTenantId(tenantId);
        disposableAssetRequest.setDrNo(drNumber);

        DisposableAsset disposableAsset = DisposableAssetMapper.toEntity(disposableAssetRequest);

         //Link each detail to the parent
        if(disposableAsset.getDisposableAssetDetail() !=null){
            for(DisposableFixedAssetDetail detail: disposableAsset.getDisposableAssetDetail()){
                detail.setDisposableAsset(disposableAsset);
            }

        }

        //save parent and cascade children

        DisposableAsset savedAsset = disposableAssetRepository.save(disposableAsset);

        return DisposableAssetMapper.toResponse(savedAsset);
    }

    /*
        GET all Disposal COLLECTION paginated
     */

    public Page<DisposableAssetResponse> getAllDisposalCollection(UUID tenantId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());

        return disposableAssetRepository.findByTenantId(tenantId,pageable).map(DisposableAssetMapper::toResponse);
    }

       /*
         Get Disposable asset by id
        */
    public DisposableAssetResponse getDisposalCollectionById(UUID tenantId, UUID id) {
        DisposableAsset disposableAsset= disposableAssetRepository.findById(id)
                .filter(da -> da.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Disposal Collection Not Found or tenant Mismatch"));

       return DisposableAssetMapper.toResponse(disposableAsset);
    }

    public void deleteDisposableAsset(UUID tenantId, UUID id) {
        DisposableAsset disposableAsset = disposableAssetRepository.findById(id)
                .filter(da -> da.getTenantId().equals(tenantId))
                .orElseThrow( () -> new RuntimeException("Disposable Asset Not Found Or tenant mismatch "));

        disposableAssetRepository.delete(disposableAsset);
    }
}
