package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetTransferRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.service.FixedAssetTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/inventory/fixed-asset-transfer")
@Tag(name = "Fixed Asset Transfer", description = "APIs for managing fixed asset transfers")
public class FixedAssetTransferController {

    private final FixedAssetTransferService fixedAssetTransferService;

    @PostMapping("/{tenantId}/add")
    @Operation(summary = "Add a new fixed asset transfer", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created",
                    content = @Content(schema = @Schema(implementation = FixedAssetTransferResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public FixedAssetTransferResponse addFixedAssetTransfer(
            @PathVariable UUID tenantId,
            @RequestBody @Valid FixedAssetTransferRequest request) {
        return fixedAssetTransferService.addFixedAssetTransfer(tenantId, request);
    }

    @GetMapping("/{tenantId}/get-all")
    @Operation(summary = "Get all fixed asset transfers", responses = {
            @ApiResponse(responseCode = "200", description = "List of fixed asset transfers")
    })
    public ResponseEntity<Page<FixedAssetTransferResponse>> getAllFixedAssetTransfers(
            @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(fixedAssetTransferService.getAllFixedAssetTransfer(tenantId, page, size));
    }

    @GetMapping("/{tenantId}/get/{id}")
    @Operation(summary = "Get fixed asset transfer by ID")
    public FixedAssetTransferResponse getFixedAssetTransferById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id) {
        return fixedAssetTransferService.getFixedAssetTransferById(tenantId, id);
    }

    @GetMapping("/{tenantId}/get-transfer-number/{transferNumber}")
    @Operation(summary = "Get fixed asset transfer by transfer number")
    public FixedAssetTransferResponse getFixedAssetTransferByTransferNumber(
            @PathVariable UUID tenantId,
            @PathVariable String transferNumber) {
        return fixedAssetTransferService.getFixedAssetTransferByTransferNumber(tenantId, transferNumber);
    }

    @PutMapping("/{tenantId}/update/{id}")
    @Operation(summary = "Update a fixed asset transfer")
    public FixedAssetTransferResponse updateFixedAssetTransfer(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody @Valid FixedAssetTransferRequest request) {
        return fixedAssetTransferService.updateFixedAssetTransfer(tenantId, id, request);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    @Operation(summary = "Delete a fixed asset transfer")
    public void deleteFixedAssetTransfer(
            @PathVariable UUID tenantId,
            @PathVariable UUID id) {
        fixedAssetTransferService.deleteFixedAssetTransfer(tenantId, id);
    }
}
