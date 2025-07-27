package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.FixedAssetDisposalMapper;
import com.MMS.Inventory_Information.Repository.DisposableAssetRepository;
import com.MMS.Inventory_Information.Repository.FixedAssetDisposalDetailRepository;
import com.MMS.Inventory_Information.Repository.FixedAssetDisposalRepository;
import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalResponse;
import com.MMS.Inventory_Information.enums.DisposalStatus;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableAsset;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposal;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FixedAssetDisposalService {

      private final FixedAssetDisposalRepository fixedAssetDisposalRepository;
      private final FixedAssetDisposalDetailRepository fixedAssetDisposalDetailRepository;
      private final DisposableAssetRepository disposableAssetRepository;

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

    public FixedAssetDisposalResponse addFixedAssetDisposal(UUID tenantId, FixedAssetDisposalRequest request, MultipartFile file) {
        // Generate Disposal Number
        String disposalNo = generateFA_DisposalNumber(tenantId);

        // Set the generated disposal number and tenant id
        request.setTenantId(tenantId);
        request.setFixedAssetDisposalNo(disposalNo);

        // Retrieve disposal status from referenced DisposableAsset
        DisposableAsset disposableAsset = disposableAssetRepository.findById(request.getDisposableAssetId())
                .orElseThrow(() -> new RuntimeException("DisposableAsset not found with id: " + request.getDisposableAssetId()));

        //  Set default value if the disposableAsset's status is null
        DisposalStatus status = disposableAsset.getDisposalStatus() != null ? disposableAsset.getDisposalStatus() : DisposalStatus.ON_HOLD;
        request.setDisposalStatus(status);

        // Map Dto to entity
        FixedAssetDisposal fixedAssetDisposal = FixedAssetDisposalMapper.toEntity(request);

        // Set the entity relationship manually
        fixedAssetDisposal.setDisposableAsset(disposableAsset);

        // Handle the file
        try {
            if (file != null && !file.isEmpty()) {
                fixedAssetDisposal.setFileName(file.getOriginalFilename());
                fixedAssetDisposal.setFileType(file.getContentType());
                fixedAssetDisposal.setFileData(file.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }

        // Save the entity to the repository
        FixedAssetDisposal savedEntity = fixedAssetDisposalRepository.save(fixedAssetDisposal);

        // Return the response DTO
        return FixedAssetDisposalMapper.toResponse(savedEntity);
    }


    /*
    * get all paginated fixed asset disposal
    */

    public Page<FixedAssetDisposalResponse> getAllFixedAssetDisposal(UUID tenantId, int page, int size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by("createdAt").descending());

        return fixedAssetDisposalRepository.findByTenantId(tenantId,pageable)
                .map(FixedAssetDisposalMapper::toResponse);
    }


    public FixedAssetDisposalResponse getFixedAssetDisposalById(UUID tenantId, UUID id) {
        // Fetch the fixed asset disposal by ID
        FixedAssetDisposal fixedAssetDisposal = fixedAssetDisposalRepository.findById(id)
                .filter(disposal -> disposal.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Disposal not found with id: " + id));
        // Map the entity to response DTO
        return FixedAssetDisposalMapper.toResponse(fixedAssetDisposal);
    }



    public FixedAssetDisposalResponse getFixedAssetDisposalByDisposalNumber(UUID tenantId, String disposalNumber) {
    // Fetch the fixed asset disposal by disposal number
        FixedAssetDisposal fixedAssetDisposal = fixedAssetDisposalRepository.findByTenantIdAndFixedAssetDisposalNo(tenantId, disposalNumber)
                .orElseThrow(() -> new RuntimeException("Fixed Asset Disposal not found with disposal number: " + disposalNumber));
        // Map the entity to response DTO
        return FixedAssetDisposalMapper.toResponse(fixedAssetDisposal);
    }

    @Transactional
    public FixedAssetDisposalResponse updateFixedAssetDisposal(UUID tenantId, UUID id, FixedAssetDisposalRequest request, MultipartFile file) {
        // Fetch the existing fixed asset disposal by id
        FixedAssetDisposal existingDisposal = fixedAssetDisposalRepository
                .findById(id)
                .filter(disposal -> disposal.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Fixed Asset Disposal not found with id: " + id));

        // Update status from DisposableAsset reference
        DisposableAsset disposableAsset = disposableAssetRepository.findById(request.getDisposableAssetId())
                .orElseThrow(() -> new RuntimeException("DisposableAsset not found with id: " + request.getDisposableAssetId()));
        request.setDisposalStatus(disposableAsset.getDisposalStatus());

        // Update the mutable fields
        FixedAssetDisposalMapper.updateEntity(existingDisposal, request);

        // Handle file upload if present
        try {
            if (file != null && !file.isEmpty()) {
                existingDisposal.setFileName(file.getOriginalFilename());
                existingDisposal.setFileType(file.getContentType());
                existingDisposal.setFileData(file.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read uploaded file", e);
        }

        // Handle disposal details: remove existing and add new
       FixedAssetDisposal updatedEntity=fixedAssetDisposalRepository.save(existingDisposal);

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
