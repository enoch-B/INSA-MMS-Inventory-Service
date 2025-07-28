package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.LostStockItemRequest;
import com.MMS.Inventory_Information.dto.response.LostStockItemResponse;
import com.MMS.Inventory_Information.model.LostStockItem.LostStockItem;
import com.MMS.Inventory_Information.service.LostStockItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/lost-stock-item")
@Tag(name = "Lost Stock Item", description = "APIs for managing lost stock items")
public class LostStockItemController {
    private final LostStockItemService lostStockItemService;

    @Operation(summary = "Add Lost Stock Item",
            description = "Create a new lost stock item record with optional file upload for the given tenant")
    @PostMapping(value = "/{tenantId}/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LostStockItemResponse> addLostStockItem(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Stock Item request body") @RequestPart("request") @Valid LostStockItemRequest request,
            @Parameter(description = "Optional file upload") @RequestPart(value = "file", required = false) MultipartFile file) {

        LostStockItemResponse response = lostStockItemService.addLostStockItem(tenantId, request, file);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get All Lost Stock Items (Paginated)",
            description = "Retrieve paginated list of lost stock items for a tenant")
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<LostStockItemResponse>> getAllLostStockItem(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        Page<LostStockItemResponse> response = lostStockItemService.getAllLostStockItem(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Lost Stock Item by ID",
            description = "Retrieve a lost stock item by tenant ID and item ID")
    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<LostStockItemResponse> getLostStockItemById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Stock Item ID") @PathVariable UUID id) {
        LostStockItemResponse response = lostStockItemService.getLostStockItemById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Lost Stock Item",
            description = "Delete a lost stock item record by tenant ID and item ID")
    @DeleteMapping("/{tenantId}/delete/{id}")
    public ResponseEntity<String> deleteLostStockItem(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Stock Item ID") @PathVariable UUID id) {
        lostStockItemService.deleteLostStockItem(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @Operation(summary = "Update Lost Stock Item",
            description = "Update an existing lost stock item with optional file upload")
    @PutMapping(value = "/{tenantId}/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LostStockItem> updateLostStockItem(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Lost Stock Item ID") @PathVariable UUID id,
            @Parameter(description = "Updated Lost Stock Item request body") @RequestPart("request") @Valid LostStockItemRequest request,
            @Parameter(description = "Optional file upload") @RequestPart(value = "file", required = false) MultipartFile file) {

        LostStockItem updated = lostStockItemService.updateLostStockItem(tenantId, id, request, file);
        return ResponseEntity.ok(updated);
    }
}
