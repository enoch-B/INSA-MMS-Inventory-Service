package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
import com.MMS.Inventory_Information.service.InventoryCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/inventory-count")
public class InventoryCountController {

     private final InventoryCountService inventoryCountService;


    //Methods for handling inventory count operations will be added here
    @PostMapping("{tenantId}/add")
    public ResponseEntity<String> createInventoryCount(@PathVariable UUID tenantId,
                                                       @RequestBody InventoryCountRequest inventoryCountRequest) {
        inventoryCountService.createInventoryCount(tenantId, inventoryCountRequest);
        return ResponseEntity.ok("Inventory Count Created Successfully");
    }

}
