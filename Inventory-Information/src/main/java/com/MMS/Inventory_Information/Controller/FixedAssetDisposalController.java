package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalResponse;
import com.MMS.Inventory_Information.service.FixedAssetDisposalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/fixed-asset-disposal")
@RequiredArgsConstructor
@Tag(name = "Fixed Asset Disposal", description = "Operations for managing fixed asset disposal records")
public class FixedAssetDisposalController {

    private final FixedAssetDisposalService fixedAssetDisposalService;

    @PostMapping(value = "/{tenantId}/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add a fixed asset disposal record",
            description = "Creates a new fixed asset disposal record with optional file upload",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Disposal record created"),
                    @ApiResponse(responseCode = "400", description = "Validation or input error", content = @Content)
            }
    )
    public FixedAssetDisposalResponse addFixedAssetDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Fixed Asset Disposal Request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FixedAssetDisposalRequest.class))
            )
            @RequestPart("request") @Valid FixedAssetDisposalRequest request,
            @Parameter(description = "Optional file attachment") @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return fixedAssetDisposalService.addFixedAssetDisposal(tenantId, request, file);
    }

    @PutMapping(value = "/{tenantId}/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update an existing fixed asset disposal record",
            description = "Updates a fixed asset disposal record by ID and tenant, with optional file upload",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    public ResponseEntity<?> updateFixedAssetDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Disposal ID") @PathVariable UUID id,
            @RequestPart("request") @Valid FixedAssetDisposalRequest request,
            @Parameter(description = "Optional file attachment") @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        FixedAssetDisposalResponse response = fixedAssetDisposalService.updateFixedAssetDisposal(tenantId, id, request, file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tenantId}/get-all")
    @Operation(
            summary = "Get all fixed asset disposals for a tenant",
            description = "Returns a paginated list of fixed asset disposal records for a specific tenant",
            responses = @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    )
    public ResponseEntity<?> getAllFixedAssetDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<FixedAssetDisposalResponse> response = fixedAssetDisposalService.getAllFixedAssetDisposal(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tenantId}/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get a fixed asset disposal record by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Record found"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    public FixedAssetDisposalResponse getFixedAssetDisposalById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Disposal ID") @PathVariable UUID id
    ) {
        return fixedAssetDisposalService.getFixedAssetDisposalById(tenantId, id);
    }

    @GetMapping("/{tenantId}/get/disposal-number/{disposalNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get a fixed asset disposal record by disposal number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Record found"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    public FixedAssetDisposalResponse getFixedAssetDisposalByDisposalNumber(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Disposal Number") @PathVariable String disposalNumber
    ) {
        return fixedAssetDisposalService.getFixedAssetDisposalByDisposalNumber(tenantId, disposalNumber);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a fixed asset disposal record",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    public String deleteFixedAssetDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Disposal ID") @PathVariable UUID id
    ) {
        fixedAssetDisposalService.deleteFixedAssetDisposal(tenantId, id);
        return "Fixed Asset Disposal deleted successfully";
    }
}
