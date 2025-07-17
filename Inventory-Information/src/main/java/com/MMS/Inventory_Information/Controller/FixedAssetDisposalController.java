package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalResponse;
import com.MMS.Inventory_Information.service.FixedAssetDisposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/fixed-asset-disposal")
@RequiredArgsConstructor
public class FixedAssetDisposalController {
            private final FixedAssetDisposalService fixedAssetDisposalService;
    // Define endpoints for Fixed Asset Disposal operations here

    @PostMapping("/{tenantId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public FixedAssetDisposalResponse addFixedAssetDisposal(@PathVariable UUID tenantId,@RequestBody FixedAssetDisposalRequest request) {
        // Call the service to handle the request
        return fixedAssetDisposalService.addFixedAssetDisposal(tenantId, request);
    }

    @GetMapping("/{tenantId}/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<FixedAssetDisposalResponse> getAllFixedAssetDisposals(@PathVariable UUID tenantId) {
        // Call the service to get all fixed asset disposals
        return fixedAssetDisposalService.getAllFixedAssetDisposals(tenantId);
    }
    @GetMapping("/{tenantId}/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetDisposalResponse getFixedAssetDisposalById(@PathVariable UUID tenantId, @PathVariable UUID id) {
        // Call the service to get a fixed asset disposal by ID
        return fixedAssetDisposalService.getFixedAssetDisposalById(tenantId, id);
    }
    @GetMapping("/{tenantId}/get/disposal-number/{disposalNumber}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetDisposalResponse getFixedAssetDisposalByDisposalNumber(@PathVariable UUID tenantId, @PathVariable String disposalNumber) {
        // Call the service to get a fixed asset disposal by disposal number
        return fixedAssetDisposalService.getFixedAssetDisposalByDisposalNumber(tenantId, disposalNumber);
    }
    @PutMapping("/{tenantId}/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetDisposalResponse updateFixedAssetDisposal(@PathVariable UUID tenantId, @PathVariable UUID id, @RequestBody FixedAssetDisposalRequest request) {
        // Call the service to update a fixed asset disposal
        return fixedAssetDisposalService.updateFixedAssetDisposal(tenantId, id, request);
    }
    @DeleteMapping("/{tenantId}/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFixedAssetDisposal(@PathVariable UUID tenantId, @PathVariable UUID id) {
        // Call the service to delete a fixed asset disposal
        fixedAssetDisposalService.deleteFixedAssetDisposal(tenantId, id);
        return "Fixed Asset Disposal deleted successfully";
    }
}
