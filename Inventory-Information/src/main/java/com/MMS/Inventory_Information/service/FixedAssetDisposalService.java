package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.FixedAssetDisposalMapper;
import com.MMS.Inventory_Information.Repository.FixedAssetDisposalDetailRepository;
import com.MMS.Inventory_Information.Repository.FixedAssetDisposalRepository;
import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalResponse;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposal;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FixedAssetDisposalService {

      private final FixedAssetDisposalRepository fixedAssetDisposalRepository;
      private final FixedAssetDisposalDetailRepository fixedAssetDisposalDetailRepository;

    public String generateFA_DisposalNumber(UUID tenantId) {
        int currentYear = LocalDate.now().getYear();
        List<String> recentNumbers = fixedAssetDisposalRepository.findRecentDisposalNumbers(tenantId, currentYear);
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
        return String.format("FixedAssetDisposalNO-%03d/%d", nextNumber, currentYear);
    }

    public FixedAssetDisposalResponse addFixedAssetDisposal(UUID tenantId, FixedAssetDisposalRequest request) {
        //Generate Disposal Number
        String disposalNo = generateFA_DisposalNumber(tenantId);
        // Set the generated disposal number and tenant id
        request.setTenantId(tenantId);
        request.setFA_disposalNo(disposalNo);
        // Map Dto to entity
        FixedAssetDisposal fixedAssetDisposal = FixedAssetDisposalMapper.toEntity(request);
        // Save the entity to the repository
        FixedAssetDisposal savedEntity = fixedAssetDisposalRepository.save(fixedAssetDisposal);
        // Return the response DTO
        return FixedAssetDisposalMapper.toResponse(savedEntity);

    }

    public List<FixedAssetDisposalResponse> getAllFixedAssetDisposals(UUID tenantId) {
        // Fetch all fixed asset disposals for the given tenant
        List<FixedAssetDisposal> disposals = fixedAssetDisposalRepository.findAllByTenantId(tenantId);
        // Map the entities to response DTOs
        return disposals.stream()
                .map(FixedAssetDisposalMapper::toResponse)
                .toList();

    }

    public FixedAssetDisposalResponse getFixedAssetDisposalById(UUID tenantId, UUID id) {
        // Fetch the fixed asset disposal by ID
        FixedAssetDisposal fixedAssetDisposal = fixedAssetDisposalRepository.findById(id)
                .filter(disposal -> disposal.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Disposal not found with id: " + id));
        // Map the entity to response DTO
        return FixedAssetDisposalMapper.toResponse(fixedAssetDisposal);
    }


    @Transactional
    public FixedAssetDisposalResponse getFixedAssetDisposalByDisposalNumber(UUID tenantId, String disposalNumber) {
    // Fetch the fixed asset disposal by disposal number
        FixedAssetDisposal fixedAssetDisposal = fixedAssetDisposalRepository.findByTenantIdAndFA_disposalNo(tenantId, disposalNumber)
                .orElseThrow(() -> new RuntimeException("Fixed Asset Disposal not found with disposal number: " + disposalNumber));
        // Map the entity to response DTO
        return FixedAssetDisposalMapper.toResponse(fixedAssetDisposal);
    }

    public FixedAssetDisposalResponse updateFixedAssetDisposal(UUID tenantId, UUID id, FixedAssetDisposalRequest request) {
     //Fetch the existing fixed asset disposal by id
        FixedAssetDisposal existingDisposal = fixedAssetDisposalRepository
                .findById(id)
                .filter(disposal -> disposal.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Disposal not found with id: " + id));

        // Update the mutable entity with new values from the request
        FixedAssetDisposalMapper.updateEntity(existingDisposal, request);
        // Handle disposal details: replace all by new value
        fixedAssetDisposalDetailRepository.deleteAllByFixedAssetDisposalId(existingDisposal.getId());

        // Create new details from the request
        List<FixedAssetDisposalDetail> details = request.getDisposalDetails().stream().map(detail ->{
            FixedAssetDisposalDetail entity = new FixedAssetDisposalDetail();
              entity.setId(UUID.randomUUID());
            entity.setItemId(detail.getItemId());
            entity.setTagNumber(detail.getTagNumber());
            entity.setGainLossValueId(detail.getGainLossValueId());
            entity.setSellingPriceId(detail.getSellingPriceId());
            entity.setAccountCode(detail.getAccountCode());
            entity.setBookValue(detail.getBookValue());
            entity.setItemLocation(detail.getItemLocation());
            entity.setDisposalMethod(detail.getDisposalMethod());
            entity.setFixedAssetDisposal(existingDisposal);
            return entity;
        }).toList();
        existingDisposal.setDisposalDetails(details);
        // Save the updated entity
        FixedAssetDisposal updatedEntity = fixedAssetDisposalRepository.save(existingDisposal);
        // Return the response DTO
        return FixedAssetDisposalMapper.toResponse(updatedEntity);
    }

    public void deleteFixedAssetDisposal(UUID tenantId, UUID id) {
        FixedAssetDisposal fixedAssetDisposal = fixedAssetDisposalRepository
                .findById(id)
                .filter(disposal -> disposal.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Disposal not found with id: " + id));
        // Delete the fixed asset disposal
        fixedAssetDisposalRepository.delete(fixedAssetDisposal);

    }
}
