package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.StockDisposalRequest;
import com.MMS.Inventory_Information.dto.response.StockDisposalResponse;
import com.MMS.Inventory_Information.service.StockDisposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock-disposal")
@RequiredArgsConstructor
public class StockDisposalController {

    private final StockDisposalService stockDisposalService;

    // Create a new Stock Disposal
    @PostMapping("/create")
    public ResponseEntity<StockDisposalResponse> create(@RequestBody StockDisposalRequest request) {
        StockDisposalResponse response = stockDisposalService.create(request);
        return ResponseEntity.ok(response);
    }

    // Get one by ID
    @GetMapping("/{id}")
    public ResponseEntity<StockDisposalResponse> getById(@PathVariable UUID id) {
        StockDisposalResponse response = stockDisposalService.getById(id);
        return ResponseEntity.ok(response);
    }

    // Get all disposals
    @GetMapping("/all")
    public ResponseEntity<List<StockDisposalResponse>> getAll() {
        List<StockDisposalResponse> responseList = stockDisposalService.getAll();
        return ResponseEntity.ok(responseList);
    }

    // Update an existing stock disposal
    @PutMapping("/{id}")
    public ResponseEntity<StockDisposalResponse> update(@PathVariable UUID id, @RequestBody StockDisposalRequest request) {
        StockDisposalResponse updated = stockDisposalService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // Delete a stock disposal by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        stockDisposalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
