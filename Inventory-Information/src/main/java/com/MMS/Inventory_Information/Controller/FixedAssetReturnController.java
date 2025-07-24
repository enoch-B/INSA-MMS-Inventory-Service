package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetReturnRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetReturnResponse;
import com.MMS.Inventory_Information.service.FixedAssetReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/fixed-asset-return")
@RequiredArgsConstructor
public class FixedAssetReturnController {

    private final FixedAssetReturnService fixedAssetReturnService;

    @PostMapping("/{tenantId}/add")
    public FixedAssetReturnResponse addFixedAssetReturn(
            @PathVariable UUID tenantId,
            @RequestBody FixedAssetReturnRequest request) {
        return fixedAssetReturnService.addFixedAssetReturn(tenantId, request);
    }

    @GetMapping("/{tenantId}/get-all")
    public Page<FixedAssetReturnResponse> getAllFixedAssetReturns(
            @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return fixedAssetReturnService.getAllFixedAssetReturns(tenantId, page, size);
    }

    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<?> getFixedAssetReturnById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id) {
        FixedAssetReturnResponse response = fixedAssetReturnService.getFixedAssetReturnById(tenantId, id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    public ResponseEntity<String> deleteFixedAssetReturn(
            @PathVariable UUID tenantId,
            @PathVariable UUID id) {
        fixedAssetReturnService.deleteFixedAssetReturn(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<FixedAssetReturnResponse> updateFixedAssetReturn(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody FixedAssetReturnRequest request) {
        FixedAssetReturnResponse response = fixedAssetReturnService.updateFixedAssetReturn(tenantId, id, request);
        return ResponseEntity.ok(response);
    }

}
