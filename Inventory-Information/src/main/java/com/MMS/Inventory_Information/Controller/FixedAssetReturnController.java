package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.FixedAssetReturnRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetReturnResponse;
import com.MMS.Inventory_Information.service.FixedAssetReturnService;
import lombok.RequiredArgsConstructor;
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
}
