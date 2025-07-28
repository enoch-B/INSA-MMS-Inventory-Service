package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.NeedAssessmentRequest;
import com.MMS.Inventory_Information.dto.response.NeedAssessmentResponse;
import com.MMS.Inventory_Information.service.NeedAssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/Need-Assessment")
@Tag(name = "Need Assessment", description = "APIs for managing need assessments")
public class NeedAssessmentController {
    private final NeedAssessmentService needAssessmentService;

    @Operation(summary = "Add Need Assessment",
            description = "Create a new need assessment record for the specified tenant")
    @PostMapping("/{tenantId}/add")
    public ResponseEntity<NeedAssessmentResponse> addNeedAssessment(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Need Assessment request body") @RequestBody @Valid NeedAssessmentRequest needAssessmentRequest) {
        NeedAssessmentResponse response = needAssessmentService.addNeedAssessment(tenantId, needAssessmentRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all Need Assessments (paginated)",
            description = "Retrieve paginated list of need assessments for the tenant")
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<NeedAssessmentResponse>> getAllNeedAssessmentPaginated(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        Page<NeedAssessmentResponse> response = needAssessmentService.getAllNeedAssessmentPaginated(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Need Assessment by ID",
            description = "Retrieve a specific need assessment record by tenant ID and record ID")
    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<NeedAssessmentResponse> getNeedAssessmentById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Need Assessment ID") @PathVariable UUID id) {
        NeedAssessmentResponse response = needAssessmentService.getNeedAssessmentById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Need Assessment",
            description = "Delete a specific need assessment record by tenant ID and record ID")
    @DeleteMapping("/{tenantId}/delete/{id}")
    public ResponseEntity<String> deleteNeedAssessment(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Need Assessment ID") @PathVariable UUID id) {
        needAssessmentService.deleteNeedAssessment(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @Operation(summary = "Update Need Assessment",
            description = "Update an existing need assessment record by tenant ID and record ID")
    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<NeedAssessmentResponse> updateNeedAssessment(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Need Assessment ID") @PathVariable UUID id,
            @Parameter(description = "Updated need assessment request body") @RequestBody @Valid NeedAssessmentRequest request) {
        NeedAssessmentResponse updated = needAssessmentService.updateNeedAssessment(tenantId, id, request);
        return ResponseEntity.ok(updated);
    }
}
