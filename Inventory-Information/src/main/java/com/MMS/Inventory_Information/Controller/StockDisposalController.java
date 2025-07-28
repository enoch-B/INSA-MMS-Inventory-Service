package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.StockDisposalRequest;
import com.MMS.Inventory_Information.dto.response.StockDisposalResponse;
import com.MMS.Inventory_Information.model.StockDisposal.StockDisposal;
import com.MMS.Inventory_Information.service.StockDisposalService;
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
@RequestMapping("/api/inventory/stock-disposal")
@RequiredArgsConstructor
@Tag(name = "Stock Disposal", description = "APIs for managing stock disposals")
public class StockDisposalController {

    private final StockDisposalService stockDisposalService;

    @Operation(summary = "Create Stock Disposal",
            description = "Create a new stock disposal record for the specified tenant with file upload support")
    @PostMapping(value = "{tenantId}/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StockDisposalResponse> addStockDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Stock Disposal request body") @RequestPart(value = "request") @Valid StockDisposalRequest request,
            @Parameter(description = "File upload") @RequestPart(value = "file") MultipartFile file) {
        StockDisposalResponse response = stockDisposalService.addStockDisposal(tenantId, request, file);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Stock Disposal by ID",
            description = "Get a specific stock disposal record by tenant and ID")
    @GetMapping("{tenantId}/{id}")
    public ResponseEntity<StockDisposalResponse> getStockDisposalById(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Stock Disposal ID") @PathVariable UUID id) {
        StockDisposalResponse response = stockDisposalService.getStockDisposalById(tenantId, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get All Stock Disposals (paginated)",
            description = "Retrieve paginated list of stock disposals for a tenant")
    @GetMapping("{tenantId}/get-all")
    public ResponseEntity<Page<StockDisposalResponse>> getAllStockDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        Page<StockDisposalResponse> response = stockDisposalService.getAllStockDisposal(tenantId, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Stock Disposal",
            description = "Update an existing stock disposal record with file upload support")
    @PutMapping(value = "/{tenantId}/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StockDisposal> updateStockDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Stock Disposal ID") @PathVariable UUID id,
            @Parameter(description = "Updated stock disposal request body") @RequestPart("request") StockDisposalRequest request,
            @Parameter(description = "File upload") @RequestPart("file") MultipartFile file) {
        StockDisposal updated = stockDisposalService.updateStockDisposal(tenantId, id, request, file);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete Stock Disposal",
            description = "Delete a stock disposal record by tenant and ID")
    @DeleteMapping("{tenantId}/delete/{id}")
    public ResponseEntity<String> deleteStockDisposal(
            @Parameter(description = "Tenant ID") @PathVariable UUID tenantId,
            @Parameter(description = "Stock Disposal ID") @PathVariable UUID id) {
        stockDisposalService.deleteStockDisposal(tenantId, id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
