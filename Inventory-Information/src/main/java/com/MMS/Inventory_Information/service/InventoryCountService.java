package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Helper.EmployeeService;
import com.MMS.Inventory_Information.Helper.committeeService;
import com.MMS.Inventory_Information.Repository.InventoryCountDetailRepository;
import com.MMS.Inventory_Information.Repository.InventoryCountRepository;
import com.MMS.Inventory_Information.dto.request.InventoryCountDetailRequest;
import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
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

        // Get snapshot values from other microservices (mocked for now)
        String committeeName = committeeService.getCommitteeNameById(request.getCommitteeId());
        List<String> memberNames = employeeService.getEmployeeNamesByIds(request.getCommitteeMemberIds());

        //  Build the main InventoryCount entity
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

        // 🧾 Convert InventoryCountDetailRequest → InventoryDetail entities
        List<InventoryDetail> details = request.getInventoryItems().stream().map(itemRequest -> {
            InventoryDetail detail = new InventoryDetail();
            detail.setTenantId(tenantId);
            detail.setInventoryCount(inventoryCount); // establish relationship
            detail.setItemId(itemRequest.getItemId());
            detail.setItemCode(itemRequest.getItemCode());
            detail.setQuantity(itemRequest.getQuantity());
            detail.setRemark(itemRequest.getRemark());
            return detail;
        }).collect(Collectors.toList());

        // 📎 Attach details to parent entity
        inventoryCount.setInventoryDetails(details);

        // 💾 Save parent and details (cascade = ALL ensures detail saving)
        inventoryCountRepository.save(inventoryCount);
    }
}
