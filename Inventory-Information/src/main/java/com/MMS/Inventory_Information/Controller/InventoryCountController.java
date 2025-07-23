package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
import com.MMS.Inventory_Information.dto.response.InventoryCountResponse;
import com.MMS.Inventory_Information.service.InventoryCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/inventory-count")
public class InventoryCountController {

    private final InventoryCountService inventoryCountService;

    @PostMapping("{tenantId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryCountResponse createInventoryCount(@PathVariable UUID tenantId,
                                                       @RequestBody InventoryCountRequest inventoryCountRequest) {
      return  inventoryCountService.createInventoryCount(tenantId, inventoryCountRequest);

    }

    @GetMapping("/{tenantId}/{id}")
    public ResponseEntity<InventoryCountResponse> getInventoryCountById(@PathVariable UUID tenantId,
                                                                        @PathVariable UUID id) {
        InventoryCountResponse response = inventoryCountService.getInventoryCountById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    // Suggested simpler path: /{tenantId}/get-all for paginated get-all
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<InventoryCountResponse>> getInventoryCountsPaginated(
            @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<InventoryCountResponse> response = inventoryCountService.getPaginatedInventoryCounts(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<InventoryCountResponse> updateInventoryCount(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody InventoryCountRequest inventoryCountRequest) {
        InventoryCountResponse response = inventoryCountService.updateInventoryCount(tenantId, id, inventoryCountRequest);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{tenantId}/{id}")
    public ResponseEntity<String> deleteInventoryCount(@PathVariable UUID tenantId,
                                                       @PathVariable UUID id) {
        inventoryCountService.deleteInventoryCount(tenantId, id);
        return ResponseEntity.ok("Inventory Count Deleted Successfully");
    }
}