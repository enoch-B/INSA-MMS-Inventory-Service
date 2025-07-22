package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.NeedAssessmentRequest;
import com.MMS.Inventory_Information.dto.response.NeedAssessmentResponse;
import com.MMS.Inventory_Information.service.NeedAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/Need-Assessment/")
public class NeedAssessmentController {
    private final NeedAssessmentService needAssessmentService;

    @PostMapping("/{tenantId}/add")
    public ResponseEntity<NeedAssessmentResponse> addNeedAssessment(@PathVariable UUID tenantId, @RequestBody NeedAssessmentRequest needAssessmentRequest) {

        NeedAssessmentResponse response = needAssessmentService.addNeedAssessment(tenantId, needAssessmentRequest);

        return ResponseEntity.ok(response);
    }

    // Suggested simpler path: /{tenantId} for paginated get-all
    @GetMapping("/{tenantId}")
    public ResponseEntity<Page<NeedAssessmentResponse>> getAllNeedAssessmentPaginated(
            @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<NeedAssessmentResponse> response = needAssessmentService.getAllNeedAssessmentPaginated(tenantId,page,size);

        return ResponseEntity.ok(response);
    }
}