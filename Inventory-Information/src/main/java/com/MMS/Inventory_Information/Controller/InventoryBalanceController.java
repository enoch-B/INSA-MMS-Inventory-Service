package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.InventoryBalanceRequest;
import com.MMS.Inventory_Information.dto.response.InventoryBalanceResponse;
import com.MMS.Inventory_Information.service.InventoryBalanceService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @PostMapping("/{tenantId}/add")
    public ResponseEntity<?> createInventoryBalance(@PathVariable UUID tenantId, @RequestBody InventoryBalanceRequest inventoryBalanceRequest){
       InventoryBalanceResponse response= inventoryBalanceService.createInventoryBalance(tenantId, inventoryBalanceRequest);
        return ResponseEntity.ok(response);
    }

      /*
      *   get all inventory balance paginated
       */
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<?> getAllInventoryBalance(@PathVariable UUID tenantId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size){

        Page<InventoryBalanceResponse> response = inventoryBalanceService.getAllInventoryBalance(tenantId,page,size);

        return ResponseEntity.ok(response);
    }

    /*
      Get inventory balance by id
     */

    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<?> getInventoryBalanceById(@PathVariable UUID tenantId,@PathVariable UUID id){

        InventoryBalanceResponse response=inventoryBalanceService.getInventoryBalanceById(tenantId,id);

        return ResponseEntity.ok(response);
    }
    /*
     * delete inventory balance
     */
    @DeleteMapping("{tenantId}/delete/{id}")
    public ResponseEntity<?> deleteInventoryBalance(@PathVariable UUID tenantId,@PathVariable UUID id){
        inventoryBalanceService.deleteInventoryBalance(tenantId,id);

        return ResponseEntity.ok("Deleted Successfully");
    }

}
