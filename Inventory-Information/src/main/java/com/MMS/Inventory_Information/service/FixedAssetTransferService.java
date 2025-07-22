package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.FixedAssetTransferMapper;
import com.MMS.Inventory_Information.Repository.FixedAssetTransferDetailRepository;
import com.MMS.Inventory_Information.Repository.FixedAssetTransferRepository;
import com.MMS.Inventory_Information.dto.request.FixedAssetTransferRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransfer;
import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransferDetail;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FixedAssetTransferService {

     private final FixedAssetTransferRepository fixedAssetTransferRepository;
     private final FixedAssetTransferDetailRepository fixedAssetTransferDetailRepository;

    public String generateFixedAssetTransferNumber(UUID tenantId) {
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

  @Transactional
    public FixedAssetTransferResponse addFixedAssetTransfer(UUID tenantId, FixedAssetTransferRequest fixedAssetTransferRequest) {
        //Generate transfer number
        String transferNo = generateFixedAssetTransferNumber(tenantId);

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

    public FixedAssetTransferResponse getFixedAssetTransferById(UUID tenantId, UUID id) {
        FixedAssetTransfer fixedAssetTransfer = fixedAssetTransferRepository
                .findById(id)
                .filter(transfer -> transfer.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Transfer not found with id: " + id));

        return FixedAssetTransferMapper.toResponse(fixedAssetTransfer);
    }

    public FixedAssetTransferResponse getFixedAssetTransferByTransferNumber(UUID tenantId, String transferNumber) {
        FixedAssetTransfer fixedAssetTransfer = fixedAssetTransferRepository
                .findByTenantIdAndTransferNo(tenantId, transferNumber)
                .orElseThrow(() -> new RuntimeException("Fixed Asset Transfer not found with transfer number: " + transferNumber));

        return FixedAssetTransferMapper.toResponse(fixedAssetTransfer);
    }

    @Transactional
    public FixedAssetTransferResponse updateFixedAssetTransfer(UUID tenantId, UUID id, FixedAssetTransferRequest fixedAssetTransferRequest) {
        FixedAssetTransfer existingTransfer = fixedAssetTransferRepository
                .findById(id)
                .filter(transfer -> transfer.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Transfer not found with id: " + id));

        // update mutable fields
        FixedAssetTransferMapper.updateEntity(existingTransfer, fixedAssetTransferRequest);

        // Map and assign new details (Hibernate will handle orphan cleanup)
        List<FixedAssetTransferDetail> details = fixedAssetTransferRequest.getTransferDetails().stream().map(detail -> {
            FixedAssetTransferDetail entity = new FixedAssetTransferDetail();
            entity.setId(UUID.randomUUID());
            entity.setItemId(detail.getItemId());
            entity.setTagNumber(detail.getTagNumber());
            entity.setBookValue(detail.getBookValue());
            entity.setAccountCode(detail.getAccountCode());
            entity.setQuantity(detail.getQuantity());
            entity.setRemark(detail.getRemark());
            entity.setDescription(detail.getDescription());
            entity.setFixedAssetTransfer(existingTransfer);
            return entity;
        }).toList();

        existingTransfer.setTransferDetails(details);

        FixedAssetTransfer saved = fixedAssetTransferRepository.save(existingTransfer);
        return FixedAssetTransferMapper.toResponse(saved);
    }



    public void deleteFixedAssetTransfer(UUID tenantId, UUID id) {
        FixedAssetTransfer fixedAssetTransfer = fixedAssetTransferRepository
                .findById(id)
                .filter(transfer -> transfer.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Transfer not found with id: " + id));

         fixedAssetTransferRepository.delete(fixedAssetTransfer);
    }
}
