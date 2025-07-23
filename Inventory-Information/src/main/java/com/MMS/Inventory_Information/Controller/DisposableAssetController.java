package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.DisposableAssetRequest;
import com.MMS.Inventory_Information.dto.response.DisposableAssetResponse;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableAsset;
import com.MMS.Inventory_Information.service.DisposableAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/disposal-collection")
@RequiredArgsConstructor
public class DisposableAssetController {
    private final DisposableAssetService disposableAssetService;

    // POST request
    @PostMapping("{tenantId}/add")
    public ResponseEntity<DisposableAssetResponse> addDisposalCollection(@PathVariable UUID tenantId, @RequestBody DisposableAssetRequest disposableAssetRequest) {
        DisposableAssetResponse response = disposableAssetService.addDisposalCollection(tenantId,disposableAssetRequest);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<DisposableAssetResponse>> getAllDisposalCollection(@PathVariable UUID tenantId, @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size){
        Page<DisposableAssetResponse> response = disposableAssetService.getAllDisposalCollection(tenantId,page,size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<DisposableAssetResponse> getDisposalCollectionById(@PathVariable UUID tenantId,@PathVariable UUID id){

        DisposableAssetResponse response = disposableAssetService.getDisposalCollectionById(tenantId,id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    public ResponseEntity<String> deleteDisposableAsset(@PathVariable UUID tenantId, @PathVariable UUID id){
        disposableAssetService.deleteDisposableAsset(tenantId,id);

        return ResponseEntity.ok("Deleted Successfully");
    }
    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<DisposableAssetResponse> updateDisposableAsset(@PathVariable UUID tenantId, @PathVariable UUID id, @RequestBody DisposableAssetRequest disposableAssetRequest) {
        DisposableAssetResponse response = disposableAssetService.updateDisposableAsset(tenantId, id, disposableAssetRequest);
        return ResponseEntity.ok(response);
    }



}
