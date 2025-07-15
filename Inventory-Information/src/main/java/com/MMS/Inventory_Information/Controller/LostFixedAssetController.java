package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.service.LostFixedAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/lost-fixed-assets/")
public class LostFixedAssetController {

    private final LostFixedAssetService lostFixedAssetService;

    @PostMapping("/{tenantId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addLostFixedAsset(@PathVariable UUID tenantId, @RequestBody LostFixedAssetRequest lostFixedAssetRequest) {
        // logic for adding lost fixed asset
        lostFixedAssetService.addLostFixedAsset(tenantId, lostFixedAssetRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Lost Fixed Asset added successfully");
    }


}
