package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.dto.response.LostFixedAssetResponse;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import com.MMS.Inventory_Information.service.LostFixedAssetService;
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
@RequestMapping("/api/inventory/lost-fixed-assets")
@Tag(name = "Lost Fixed Asset", description = "APIs for managing lost fixed assets")
public class LostFixedAssetController {

    private final LostFixedAssetService lostFixedAssetService;

    @Operation(summary = "Add Lost Fixed Asset",
            description = "Create a new lost fixed asset record for the given tenant")
    @PostMapping("/{tenantId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public LostFixedAssetResponse addLostFixedAsset(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Fixed Asset request body") @RequestBody @Valid LostFixedAssetRequest lostFixedAssetRequest) {
        return lostFixedAssetService.addLostFixedAsset(tenantId, lostFixedAssetRequest);
    }

    @Operation(summary = "Get All Lost Fixed Assets",
            description = "Retrieve paginated list of all lost fixed assets for the tenant")
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<LostFixedAssetResponse>> getAllLostFixedAssets(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        Page<LostFixedAssetResponse> response = lostFixedAssetService.getAllLostFixedAssets(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Lost Fixed Asset by ID",
            description = "Retrieve a lost fixed asset record by tenant ID and asset ID")
    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<LostFixedAssetResponse> getLostFixedAssetById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Fixed Asset ID") @PathVariable UUID id) {
        LostFixedAssetResponse response = lostFixedAssetService.getLostFixedAssetById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Lost Fixed Asset",
            description = "Update an existing lost fixed asset record by tenant ID and asset ID")
    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<LostFixedAsset> updateLostFixedAsset(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Fixed Asset ID") @PathVariable UUID id,
            @Parameter(description = "Updated lost fixed asset request body") @RequestBody @Valid LostFixedAssetRequest request) {
        LostFixedAsset updated = lostFixedAssetService.updateLostFixedAsset(tenantId, id, request);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete Lost Fixed Asset",
            description = "Delete a lost fixed asset record by tenant ID and asset ID")
    @DeleteMapping("/{tenantId}/delete/{id}")
    public ResponseEntity<String> deleteLostFixedAsset(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Fixed Asset ID") @PathVariable UUID id) {
        lostFixedAssetService.deleteLostFixedAsset(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
