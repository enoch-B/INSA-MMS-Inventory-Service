package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
import com.MMS.Inventory_Information.dto.response.InventoryCountResponse;
import com.MMS.Inventory_Information.service.InventoryCountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/inventory-count")
@Tag(name = "Inventory Count", description = "APIs for managing inventory counts")
public class InventoryCountController {

    private final InventoryCountService inventoryCountService;

    @Operation(summary = "Create a new Inventory Count",
            description = "Create a new inventory count record for the specified tenant")
    @PostMapping("{tenantId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryCountResponse createInventoryCount(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Inventory Count request body") @RequestBody @Valid InventoryCountRequest inventoryCountRequest) {
        return inventoryCountService.createInventoryCount(tenantId, inventoryCountRequest);
    }

    @Operation(summary = "Get Inventory Count by ID",
            description = "Retrieve a specific inventory count record by tenant ID and record ID")
    @GetMapping("/{tenantId}/{id}")
    public ResponseEntity<InventoryCountResponse> getInventoryCountById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Inventory Count ID") @PathVariable UUID id) {
        InventoryCountResponse response = inventoryCountService.getInventoryCountById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all Inventory Counts (paginated)",
            description = "Retrieve paginated list of inventory counts for a tenant")
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<InventoryCountResponse>> getInventoryCountsPaginated(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        Page<InventoryCountResponse> response = inventoryCountService.getPaginatedInventoryCounts(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Inventory Count",
            description = "Update an existing inventory count record by tenant ID and record ID")
    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<InventoryCountResponse> updateInventoryCount(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Inventory Count ID") @PathVariable UUID id,
            @Parameter(description = "Updated inventory count request body") @RequestBody @Valid InventoryCountRequest inventoryCountRequest) {
        InventoryCountResponse response = inventoryCountService.updateInventoryCount(tenantId, id, inventoryCountRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Inventory Count",
            description = "Delete a specific inventory count record by tenant ID and record ID")
    @DeleteMapping("/{tenantId}/{id}")
    public ResponseEntity<String> deleteInventoryCount(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Inventory Count ID") @PathVariable UUID id) {
        inventoryCountService.deleteInventoryCount(tenantId, id);
        return ResponseEntity.ok("Inventory Count Deleted Successfully");
    }
}
