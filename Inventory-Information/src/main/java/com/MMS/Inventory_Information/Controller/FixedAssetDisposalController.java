package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalResponse;
import com.MMS.Inventory_Information.service.FixedAssetDisposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory/fixed-asset-disposal")
@RequiredArgsConstructor
public class FixedAssetDisposalController {

    private final FixedAssetDisposalService fixedAssetDisposalService;

    // 🔄 POST - with multipart support
    @PostMapping(value = "/{tenantId}/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FixedAssetDisposalResponse addFixedAssetDisposal(
            @PathVariable UUID tenantId,
            @RequestPart("request") FixedAssetDisposalRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return fixedAssetDisposalService.addFixedAssetDisposal(tenantId, request, file);
    }

    // 🔄 PUT - with multipart support
    @PutMapping(value = "/{tenantId}/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetDisposalResponse updateFixedAssetDisposal(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestPart("request") FixedAssetDisposalRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return fixedAssetDisposalService.updateFixedAssetDisposal(tenantId, id, request, file);
    }

    @GetMapping("/{tenantId}/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<FixedAssetDisposalResponse> getAllFixedAssetDisposals(@PathVariable UUID tenantId) {
        return fixedAssetDisposalService.getAllFixedAssetDisposals(tenantId);
    }

    @GetMapping("/{tenantId}/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetDisposalResponse getFixedAssetDisposalById(@PathVariable UUID tenantId, @PathVariable UUID id) {
        return fixedAssetDisposalService.getFixedAssetDisposalById(tenantId, id);
    }

    @GetMapping("/{tenantId}/get/disposal-number/{disposalNumber}")
    @ResponseStatus(HttpStatus.OK)
    public FixedAssetDisposalResponse getFixedAssetDisposalByDisposalNumber(@PathVariable UUID tenantId, @PathVariable String disposalNumber) {
        return fixedAssetDisposalService.getFixedAssetDisposalByDisposalNumber(tenantId, disposalNumber);
    }

    @DeleteMapping("/{tenantId}/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFixedAssetDisposal(@PathVariable UUID tenantId, @PathVariable UUID id) {
        fixedAssetDisposalService.deleteFixedAssetDisposal(tenantId, id);
        return "Fixed Asset Disposal deleted successfully";
    }
}
