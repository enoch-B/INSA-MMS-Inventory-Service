package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetTransferRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.service.FixedAssetTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public FixedAssetTransferResponse addFixedAssetTransfer(@PathVariable UUID tenantId, @RequestBody FixedAssetTransferRequest fixedAssetTransferRequest){

        return fixedAssetTransferService.addFixedAssetTransfer(tenantId, fixedAssetTransferRequest);
    }
    @GetMapping("/{tenantId}/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<FixedAssetTransferResponse> getAllFixedAssetTransfers(@PathVariable UUID tenantId) {
        // logic for getting all lost fixed assets
        return fixedAssetTransferService.getAllFixedAssetTransfers(tenantId);

    }

}
