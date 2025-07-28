package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetReturnRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetReturnResponse;
import com.MMS.Inventory_Information.service.FixedAssetReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/fixed-asset-return")
@RequiredArgsConstructor
@Tag(name = "Fixed Asset Return", description = "Manage fixed asset return records")
public class FixedAssetReturnController {

    private final FixedAssetReturnService fixedAssetReturnService;

    @PostMapping("/{tenantId}/add")
    @Operation(
            summary = "Add a fixed asset return record",
            description = "Creates a new record for returning fixed assets by a tenant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return recorded successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
            }
    )
    public FixedAssetReturnResponse addFixedAssetReturn(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Fixed Asset Return Request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FixedAssetReturnRequest.class))
            )
            @Valid @RequestBody FixedAssetReturnRequest request) {
        return fixedAssetReturnService.addFixedAssetReturn(tenantId, request);
    }

    @GetMapping("/{tenantId}/get-all")
    @Operation(
            summary = "Get all fixed asset returns",
            description = "Returns a paginated list of all fixed asset return records for a tenant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
            }
    )
    public Page<FixedAssetReturnResponse> getAllFixedAssetReturns(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return fixedAssetReturnService.getAllFixedAssetReturns(tenantId, page, size);
    }

    @GetMapping("/{tenantId}/get/{id}")
    @Operation(
            summary = "Get fixed asset return by ID",
            description = "Returns a specific fixed asset return record by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Record found"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    public ResponseEntity<?> getFixedAssetReturnById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Fixed Asset Return ID") @PathVariable UUID id) {
        FixedAssetReturnResponse response = fixedAssetReturnService.getFixedAssetReturnById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    @Operation(
            summary = "Delete a fixed asset return record",
            description = "Deletes a fixed asset return record by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Record deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    public ResponseEntity<String> deleteFixedAssetReturn(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Fixed Asset Return ID") @PathVariable UUID id) {
        fixedAssetReturnService.deleteFixedAssetReturn(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("/{tenantId}/update/{id}")
    @Operation(
            summary = "Update a fixed asset return record",
            description = "Updates an existing fixed asset return record for a given ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Record updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    public ResponseEntity<FixedAssetReturnResponse> updateFixedAssetReturn(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Fixed Asset Return ID") @PathVariable UUID id,
            @RequestBody @Valid FixedAssetReturnRequest request) {
        FixedAssetReturnResponse response = fixedAssetReturnService.updateFixedAssetReturn(tenantId, id, request);
        return ResponseEntity.ok(response);
    }
}
