package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.DisposableAssetRequest;
import com.MMS.Inventory_Information.dto.response.DisposableAssetResponse;
import com.MMS.Inventory_Information.service.DisposableAssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/disposal-collection")
@RequiredArgsConstructor
@Tag(name = "Disposable Assets", description = "Endpoints for managing disposable assets and disposal collections")
public class DisposableAssetController {

    private final DisposableAssetService disposableAssetService;

    @PostMapping("{tenantId}/add")
    @Operation(
            summary = "Add a new disposable asset record",
            description = "Creates a new disposal collection record for the given tenant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Disposable asset added successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input or tenant ID")
            }
    )
    public ResponseEntity<DisposableAssetResponse> addDisposalCollection(
            @Parameter(description = "Tenant ID", required = true) @PathVariable UUID tenantId,
            @Valid @RequestBody DisposableAssetRequest disposableAssetRequest) {

        DisposableAssetResponse response = disposableAssetService.addDisposalCollection(tenantId, disposableAssetRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tenantId}/get-all")
    @Operation(
            summary = "Get all disposable assets for a tenant",
            description = "Fetches a paginated list of all disposable assets associated with the specified tenant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
            }
    )
    public ResponseEntity<Page<DisposableAssetResponse>> getAllDisposalCollection(
            @Parameter(description = "Tenant ID", required = true) @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<DisposableAssetResponse> response = disposableAssetService.getAllDisposalCollection(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tenantId}/get/{id}")
    @Operation(
            summary = "Get a specific disposable asset by ID",
            description = "Returns a single disposable asset record for the given tenant and ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Disposable asset found"),
                    @ApiResponse(responseCode = "404", description = "Disposable asset not found")
            }
    )
    public ResponseEntity<DisposableAssetResponse> getDisposalCollectionById(
            @Parameter(description = "Tenant ID", required = true) @PathVariable UUID tenantId,
            @Parameter(description = "Disposable Asset ID", required = true) @PathVariable UUID id) {

        DisposableAssetResponse response = disposableAssetService.getDisposalCollectionById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    @Operation(
            summary = "Delete a disposable asset",
            description = "Deletes a disposable asset by its ID for the specified tenant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Disposable asset deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Disposable asset not found")
            }
    )
    public ResponseEntity<String> deleteDisposableAsset(
            @Parameter(description = "Tenant ID", required = true) @PathVariable UUID tenantId,
            @Parameter(description = "Disposable Asset ID", required = true) @PathVariable UUID id) {

        disposableAssetService.deleteDisposableAsset(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("/{tenantId}/update/{id}")
    @Operation(
            summary = "Update a disposable asset",
            description = "Updates the specified disposable asset with new data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Disposable asset updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input or asset ID")
            }
    )
    public ResponseEntity<DisposableAssetResponse> updateDisposableAsset(
            @Parameter(description = "Tenant ID", required = true) @PathVariable UUID tenantId,
            @Parameter(description = "Disposable Asset ID", required = true) @PathVariable UUID id,
            @Valid @RequestBody DisposableAssetRequest disposableAssetRequest) {

        DisposableAssetResponse response = disposableAssetService.updateDisposableAsset(tenantId, id, disposableAssetRequest);
        return ResponseEntity.ok(response);
    }
}
