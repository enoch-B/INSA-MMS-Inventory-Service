package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.InventoryBalanceRequest;
import com.MMS.Inventory_Information.dto.response.InventoryBalanceResponse;
import com.MMS.Inventory_Information.service.InventoryBalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/inventory-balance")
public class InventoryBalanceController {

    private final InventoryBalanceService inventoryBalanceService;

    @Operation(summary = "Create Inventory Balance", description = "Create a new inventory balance for a specific tenant")
    @PostMapping("/{tenantId}/add")
    public ResponseEntity<?> createInventoryBalance(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @RequestBody @Valid InventoryBalanceRequest inventoryBalanceRequest) {
        InventoryBalanceResponse response = inventoryBalanceService.createInventoryBalance(tenantId, inventoryBalanceRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get All Inventory Balances (Paginated)", description = "Get all inventory balance records for a tenant")
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<?> getAllInventoryBalance(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Page number (default = 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size (default = 10)") @RequestParam(defaultValue = "10") int size) {
        Page<InventoryBalanceResponse> response = inventoryBalanceService.getAllInventoryBalance(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Inventory Balance by ID", description = "Get a specific inventory balance by its ID")
    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<?> getInventoryBalanceById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Inventory Balance ID") @PathVariable UUID id) {
        InventoryBalanceResponse response = inventoryBalanceService.getInventoryBalanceById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Inventory Balance", description = "Delete a specific inventory balance by its ID")
    @DeleteMapping("{tenantId}/delete/{id}")
    public ResponseEntity<?> deleteInventoryBalance(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Inventory Balance ID") @PathVariable UUID id) {
        inventoryBalanceService.deleteInventoryBalance(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
