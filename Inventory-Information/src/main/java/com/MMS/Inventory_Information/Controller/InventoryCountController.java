package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
import com.MMS.Inventory_Information.dto.response.InventoryCountResponse;
import com.MMS.Inventory_Information.service.InventoryCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/inventory-count")
public class InventoryCountController {

    private final InventoryCountService inventoryCountService;

    /**
     * Create a new inventory count for a given tenant
     */
    @PostMapping("{tenantId}/add")
    public ResponseEntity<String> createInventoryCount(@PathVariable UUID tenantId,
                                                       @RequestBody InventoryCountRequest inventoryCountRequest) {
        inventoryCountService.createInventoryCount(tenantId, inventoryCountRequest);
        return ResponseEntity.ok("Inventory Count Created Successfully");
    }

    /**
     * Get a single inventory count by its ID
     */
    @GetMapping("/{tenantId}/{id}")
    public ResponseEntity<InventoryCountResponse> getInventoryCountById(@PathVariable UUID tenantId,
                                                                        @PathVariable UUID id) {
        InventoryCountResponse response = inventoryCountService.getInventoryCountById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    /**
     *  Get all inventory counts for a tenant
     */
    @GetMapping("/{tenantId}/all")
    public ResponseEntity<List<InventoryCountResponse>> getAllInventoryCounts(@PathVariable UUID tenantId) {
        List<InventoryCountResponse> responseList = inventoryCountService.getAllInventoryCounts(tenantId);
        return ResponseEntity.ok(responseList);
    }

    /**
     *  Delete an inventory count by ID
     */
    @DeleteMapping("/{tenantId}/{id}")
    public ResponseEntity<String> deleteInventoryCount(@PathVariable UUID tenantId,
                                                       @PathVariable UUID id) {
        inventoryCountService.deleteInventoryCount(tenantId, id);
        return ResponseEntity.ok("Inventory Count Deleted Successfully");
    }
}
