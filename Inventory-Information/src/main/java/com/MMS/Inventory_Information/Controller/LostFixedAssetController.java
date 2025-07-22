package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.dto.response.LostFixedAssetResponse;
import com.MMS.Inventory_Information.service.LostFixedAssetService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<List<LostFixedAssetResponse>> getAllLostFixedAssets(@PathVariable UUID tenantId) {
        // logic for getting all lost fixed assets
        List<LostFixedAssetResponse> lostFixedAssetResponses = lostFixedAssetService.getAllLostFixedAssets(tenantId);
        return new ResponseEntity<>(lostFixedAssetResponses, HttpStatus.OK);
    }



}
