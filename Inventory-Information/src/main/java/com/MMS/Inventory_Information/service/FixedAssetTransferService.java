package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.FixedAssetTransferMapper;
import com.MMS.Inventory_Information.Repository.FixedAssetTransferDetailRepository;
import com.MMS.Inventory_Information.Repository.FixedAssetTransferRepository;
import com.MMS.Inventory_Information.dto.request.FixedAssetTransferRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransfer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FixedAssetTransferService {

     private final FixedAssetTransferRepository fixedAssetTransferRepository;
     private final FixedAssetTransferDetailRepository fixedAssetTransferDetailRepository;

    public String generateInventoryCountNumber(UUID tenantId) {
        int currentYear = LocalDate.now().getYear();
        List<String> recentNumbers = fixedAssetTransferRepository.findRecentTransferNumbers(tenantId, currentYear);
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
        return String.format("FixedAssetTransferNO-%03d/%d", nextNumber, currentYear);
    }

    public FixedAssetTransferResponse addFixedAssetTransfer(UUID tenantId, FixedAssetTransferRequest fixedAssetTransferRequest) {
        //Generate transfer number
        String transferNo = generateInventoryCountNumber(tenantId);

        //set the generated transfer number and tenant id
        fixedAssetTransferRequest.setTenantId(tenantId);
        fixedAssetTransferRequest.setTransferNo(transferNo);


        // Map Dto to entity
        FixedAssetTransfer transferEntity= FixedAssetTransferMapper.toEntity(fixedAssetTransferRequest);

        FixedAssetTransfer savedEntity = fixedAssetTransferRepository.save(transferEntity);

        return FixedAssetTransferMapper.toResponse(savedEntity);

    }

    public List<FixedAssetTransferResponse> getAllFixedAssetTransfers(UUID tenantId) {
        List<FixedAssetTransfer> fixedAssetTransfers = fixedAssetTransferRepository.findByTenantId(tenantId);

        return fixedAssetTransfers.stream()
                .map(FixedAssetTransferMapper::toResponse) // Make sure this exists
                .collect(Collectors.toList());
    }
}
