package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.dto.response.LostFixedAssetResponse;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import com.MMS.Inventory_Information.service.LostFixedAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/lost-fixed-assets/")
public class LostFixedAssetController {

    private final LostFixedAssetService lostFixedAssetService;

    @PostMapping("/{tenantId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public LostFixedAssetResponse addLostFixedAsset(@PathVariable UUID tenantId, @RequestBody LostFixedAssetRequest lostFixedAssetRequest) {
        // logic for adding lost fixed asset
       return  lostFixedAssetService.addLostFixedAsset(tenantId, lostFixedAssetRequest);

    }

    /*
     * Get all lost fixed assets
     */
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<?> getAllLostFixedAssets(@PathVariable UUID tenantId, @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size){
        Page<LostFixedAssetResponse> response= lostFixedAssetService.getAllLostFixedAssets(tenantId,page,size);

        return ResponseEntity.ok(response);
    }

    /*
    *  Get a single lost fixed asset by id
    */

    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<?> getLostFixedAssetById(@PathVariable UUID tenantId,@PathVariable UUID id){
        LostFixedAssetResponse response=lostFixedAssetService.getLostFixedAssetById(tenantId,id);

        return ResponseEntity.ok(response);
    }

    /*
    *  update lost fixed asset
    */

    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<?>  updateLostFixedAsset(@PathVariable UUID tenantId, @PathVariable UUID id,@RequestBody LostFixedAssetRequest request){
        LostFixedAsset updated = lostFixedAssetService.updateLostFixedAsset(tenantId,id,request);

        return ResponseEntity.ok(updated);
    }

  /*
  *  Delete by id
   */

    @DeleteMapping("/{tenantId}/delete/{id}")
    public ResponseEntity<?> deleteLostFixedAsset(@PathVariable UUID tenantId, @PathVariable UUID id){
        lostFixedAssetService.deleteLostFixedAsset(tenantId,id);

        return ResponseEntity.ok("DeletedSuccessfully");
    }



}
