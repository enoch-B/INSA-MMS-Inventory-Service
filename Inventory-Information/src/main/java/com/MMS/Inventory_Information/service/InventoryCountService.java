package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Helper.EmployeeService;
import com.MMS.Inventory_Information.Helper.committeeService;
import com.MMS.Inventory_Information.Repository.InventoryCountDetailRepository;
import com.MMS.Inventory_Information.Repository.InventoryCountRepository;
import com.MMS.Inventory_Information.dto.request.InventoryCountDetailRequest;
import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
import com.MMS.Inventory_Information.dto.response.InventoryCountResponse;
import com.MMS.Inventory_Information.mapper.InventoryCountMapper;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryDetail;
import lombok.RequiredArgsConstructor;
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
     */
    public void createInventoryCount(UUID tenantId, InventoryCountRequest request) {
        String committeeName = committeeService.getCommitteeNameById(request.getCommitteeId());
        List<String> memberNames = employeeService.getEmployeeNamesByIds(request.getCommitteeMemberIds());

        InventoryCount inventoryCount = new InventoryCount();
        inventoryCount.setTenantId(tenantId);
        inventoryCount.setInventoryCountNumber(generateInventoryCountNumber(tenantId));
        inventoryCount.setStoreId(request.getStoreId());
        inventoryCount.setPreparedBy(request.getPreparedBy());
        inventoryCount.setPreparedOn(LocalDate.now());
        inventoryCount.setBudgetYear(request.getBudgetYear());
        inventoryCount.setCountType(request.getCountType());
        inventoryCount.setStoreType(request.getStoreType());
        inventoryCount.setCountDate(request.getPreparedOn());

        inventoryCount.setCommitteeId(request.getCommitteeId());
        inventoryCount.setCommitteeName(committeeName);
        inventoryCount.setCommitteeMemberIds(request.getCommitteeMemberIds());
        inventoryCount.setCommitteeMemberName(memberNames);

        List<InventoryDetail> details = request.getInventoryItems().stream().map(itemRequest -> {
            InventoryDetail detail = new InventoryDetail();
            detail.setTenantId(tenantId);
            detail.setInventoryCount(inventoryCount);
            detail.setItemId(itemRequest.getItemId());
            detail.setItemCode(itemRequest.getItemCode());
            detail.setQuantity(itemRequest.getQuantity());
            detail.setRemark(itemRequest.getRemark());
            return detail;
        }).collect(Collectors.toList());

        inventoryCount.setInventoryDetails(details);
        inventoryCountRepository.save(inventoryCount);
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
     * Get all inventory counts for a tenant.
     */
    public List<InventoryCountResponse> getAllInventoryCounts(UUID tenantId) {
        return inventoryCountRepository.findAll().stream()
                .filter(ic -> ic.getTenantId().equals(tenantId))
                .map(InventoryCountMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Delete an inventory count by ID and tenant.
     */
    public void deleteInventoryCount(UUID tenantId, UUID id) {
        InventoryCount count = inventoryCountRepository.findById(id)
                .filter(ic -> ic.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("InventoryCount not found or tenant mismatch"));

        inventoryCountRepository.delete(count);
    }
}
