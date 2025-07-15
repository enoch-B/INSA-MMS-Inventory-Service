package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Repository.LostFixedAssetRepository;
import com.MMS.Inventory_Information.Repository.LostItemDetailRepository;
import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostItemDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LostFixedAssetService {

    private final LostFixedAssetRepository lostFixedAssetRepository;
    private final LostItemDetailRepository lostItemDetailRepository;

    public void addLostFixedAsset(UUID tenantId, LostFixedAssetRequest lostFixedAssetRequest) {
        // create a new LostFixedAsset entity from the request
        LostFixedAsset lostFixedAsset =  LostFixedAsset.builder()
                .id(UUID.randomUUID())
                .tenantId(tenantId)
                .lostItemNo(lostFixedAssetRequest.getLostItemNo())
                .storeId(lostFixedAssetRequest.getStoreId())
                .departmentId(lostFixedAssetRequest.getDepartmentId())
                .processedById(lostFixedAssetRequest.getProcessedById())
                .registrationDate(lostFixedAssetRequest.getRegistrationDate())
                .processedBy(lostFixedAssetRequest.getProcessedBy())
                .processedOn(lostFixedAssetRequest.getProcessedOn())
                .filename(lostFixedAssetRequest.getFileName())
                .fileType(lostFixedAssetRequest.getFileType())
                .data(lostFixedAssetRequest.getData())
                .build();

        // save the LostFixedAsset entity
        var detailEntities = lostFixedAssetRequest.getLostItemDetails().stream().map(detail -> LostItemDetail.builder()
                .id(UUID.randomUUID())
                .itemId(detail.getItemId())
                .tagNo(detail.getTagNo())
                .bookValue(detail.getBookValue())
                .accountCode(detail.getAccountCode())
                .duration(detail.getDuration())
                .description(detail.getDescription())
                .remark(detail.getRemark())
                .lostFixedAsset(lostFixedAsset)
                .build()).collect(Collectors.toList());

        lostFixedAsset.setLostItemDetails(detailEntities);

        lostFixedAssetRepository.save(lostFixedAsset);


    }
}
