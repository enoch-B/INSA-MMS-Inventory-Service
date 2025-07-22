package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Helper.EmployeeService;
import com.MMS.Inventory_Information.Helper.committeeService;
import com.MMS.Inventory_Information.Repository.InventoryCountDetailRepository;
import com.MMS.Inventory_Information.Repository.InventoryCountRepository;
import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
import com.MMS.Inventory_Information.dto.response.InventoryCountResponse;
import com.MMS.Inventory_Information.Mapper.InventoryCountMapper;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryDetail;
import jakarta.transaction.Transactional;
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
public class InventoryCountService {

    private final InventoryCountRepository inventoryCountRepository;
    private final InventoryCountDetailRepository inventoryCountDetailRepository;
    private final committeeService committeeService;
    private final EmployeeService employeeService;

    /**
     * Generates a unique inventory count number per tenant per year.
     */
    public String generateInventoryCountNumber(UUID tenantId) {
        int currentYear = LocalDate.now().getYear();
        List<String> recentNumbers = inventoryCountRepository.findRecentInventoryCountNumbers(tenantId, currentYear);
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

        return String.format("InventoryNO-%03d/%d", nextNumber, currentYear);
    }

    /**
     * Creates and saves an InventoryCount with its associated detail items.
     *
     *
     */

     @Transactional
    public InventoryCountResponse createInventoryCount(UUID tenantId, InventoryCountRequest request) {
        String committeeName = committeeService.getCommitteeNameById(request.getCommitteeId());
        List<String> memberNames = employeeService.getEmployeeNamesByIds(request.getCommitteeMembersId());
        String inventoryCountNumber = generateInventoryCountNumber(tenantId);

        request.setInventoryCountNumber(inventoryCountNumber);
        request.setTenantId(tenantId);
        request.setCommitteeName(committeeName);
        request.setCommitteeMembersName(memberNames);

        // ✅ Map DTO to entity
        InventoryCount inventoryCount = InventoryCountMapper.toEntity(request);

        // ✅ Link each detail to the parent count
        if (inventoryCount.getInventoryDetails() != null) {
            for (InventoryDetail detail : inventoryCount.getInventoryDetails()) {
                detail.setInventoryCount(inventoryCount);  //  This is CRUCIAL
            }
        }

        // ✅ Save parent and cascade children
        InventoryCount savedCount = inventoryCountRepository.save(inventoryCount);

        return InventoryCountMapper.toResponse(savedCount);
    }


    /**
     * Get a single inventory count by ID (mapped to response DTO).
     */
    public InventoryCountResponse getInventoryCountById(UUID tenantId, UUID id) {
        InventoryCount count = inventoryCountRepository.findById(id)
                .filter(ic -> ic.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("InventoryCount not found or tenant mismatch"));

        return InventoryCountMapper.toResponse(count);
    }

    /**
     * Get all inventory counts for a tenant (no pagination).
     */
    public List<InventoryCountResponse> getAllInventoryCounts(UUID tenantId) {
        return inventoryCountRepository.findAllByTenantId(tenantId).stream()
                .map(InventoryCountMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get paginated inventory counts for a tenant.
     */
    public Page<InventoryCountResponse> getPaginatedInventoryCounts(UUID tenantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return inventoryCountRepository.findByTenantId(tenantId, pageable)
                .map(InventoryCountMapper::toResponse);
    }

    /**
     * Delete an inventory count by ID and tenant (hard delete).
     */
    public void deleteInventoryCount(UUID tenantId, UUID id) {
        InventoryCount count = inventoryCountRepository.findById(id)
                .filter(ic -> ic.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("InventoryCount not found or tenant mismatch"));

        inventoryCountRepository.delete(count);
    }

 @Transactional
    public InventoryCountResponse updateInventoryCount(UUID tenantId, UUID id, InventoryCountRequest request) {
        InventoryCount existingCount = inventoryCountRepository.findById(id)
                .filter(ic -> ic.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Inventory Count not found or tenant mismatch"));
        // Update fields from request
        if (!existingCount.getCommitteeId().equals(request.getCommitteeId())) {
            String committeeName = committeeService.getCommitteeNameById(request.getCommitteeId());
            List<String> memberNames = employeeService.getEmployeeNamesByIds(request.getCommitteeMembersId());
            existingCount.setCommitteeName(committeeName);
            existingCount.setCommitteeMembersName(memberNames);
        }
      inventoryCountDetailRepository.deleteAllByInventoryCountId(existingCount.getId());

        List<InventoryDetail> details = request.getInventoryItems().stream().map(itemRequest -> {
            InventoryDetail detail = new InventoryDetail();
            detail.setId(UUID.randomUUID());
            detail.setItemId(itemRequest.getItemId());
            detail.setQuantity(itemRequest.getQuantity());
            detail.setRemark(itemRequest.getRemark());
            detail.setInventoryCount(existingCount);
            return detail;
        }).toList();

        existingCount.setInventoryDetails(details);

        InventoryCount updatedCount = inventoryCountRepository.save(existingCount);

        return InventoryCountMapper.toResponse(updatedCount);
    }
}
