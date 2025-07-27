package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetTransferRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.service.FixedAssetTransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/fixed-asset-transfer")
public class FixedAssetTransferController {
    private final FixedAssetTransferService fixedAssetTransferService;
    // Define endpoints for Fixed Asset Transfer operations here

    @PostMapping("/{tenantId}/add")
  public FixedAssetTransferResponse addFixedAssetTransfer(@PathVariable UUID tenantId, @RequestBody @Valid FixedAssetTransferRequest fixedAssetTransferRequest){

        return fixedAssetTransferService.addFixedAssetTransfer(tenantId, fixedAssetTransferRequest);
    }
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<?> getAllFixedAssetTransfers(@PathVariable UUID tenantId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        // logic for getting all lost fixed assets
        Page<FixedAssetTransferResponse> response= fixedAssetTransferService.getAllFixedAssetTransfer(tenantId,page,size);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{tenantId}/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetTransferResponse getFixedAssetTransferById(@PathVariable UUID tenantId, @PathVariable UUID id) {
        // logic for getting a fixed asset transfer by ID
        return fixedAssetTransferService.getFixedAssetTransferById(tenantId, id);
    }

    @GetMapping("/{tenantId}/get-transfer-number/{transferNumber}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetTransferResponse getFixedAssetTransferByTransferNumber(@PathVariable UUID tenantId, @PathVariable String transferNumber) {
        // logic for getting a fixed asset transfer by transfer number
        return fixedAssetTransferService.getFixedAssetTransferByTransferNumber(tenantId, transferNumber);
    }

    @PutMapping("/{tenantId}/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetTransferResponse updateFixedAssetTransfer(@PathVariable UUID tenantId, @PathVariable UUID id, @RequestBody @Valid FixedAssetTransferRequest request) {
        // logic for updating a fixed asset transfer
        return fixedAssetTransferService.updateFixedAssetTransfer(tenantId, id, request);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFixedAssetTransfer(@PathVariable UUID tenantId, @PathVariable UUID id) {
        // logic for deleting a fixed asset transfer
        fixedAssetTransferService.deleteFixedAssetTransfer(tenantId, id);
    }
}
