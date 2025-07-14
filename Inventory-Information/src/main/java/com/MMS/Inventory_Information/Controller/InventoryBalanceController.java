package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.InventoryBalanceRequest;
import com.MMS.Inventory_Information.service.InventoryBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/inventory-balance")
public class InventoryBalanceController {

    private final InventoryBalanceService inventoryBalanceService;

    // Define endpoints for inventory balance operations here
    @PostMapping("/{tenantId}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createInventoryBalance(@PathVariable UUID tenantId, @RequestBody InventoryBalanceRequest inventoryBalanceRequest){
        inventoryBalanceService.createInventoryBalance(tenantId, inventoryBalanceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inventory Balance created successfully");
    }
}
