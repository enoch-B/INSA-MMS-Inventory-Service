package com.MMS.Inventory_Information.Controller;

import com.MMS.Inventory_Information.dto.request.StockDisposalRequest;
import com.MMS.Inventory_Information.dto.response.StockDisposalResponse;
import com.MMS.Inventory_Information.model.StockDisposal.StockDisposal;
import com.MMS.Inventory_Information.service.StockDisposalService;
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
public class StockDisposalController {

    private final StockDisposalService stockDisposalService;

    // Create a new Stock Disposal
    @PostMapping(value= "{tenantId}/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StockDisposalResponse> addStockDisposal(@PathVariable UUID tenantId, @RequestPart(value = "request") @Valid StockDisposalRequest request,
                                                                  @RequestPart(value = "file")MultipartFile file) {
        StockDisposalResponse response = stockDisposalService.addStockDisposal(tenantId,request,file);
        return ResponseEntity.ok(response);
    }

    // Get one by ID
    @GetMapping("{tenantId}/{id}")
    public ResponseEntity<StockDisposalResponse> getStockDisposalById(@PathVariable UUID tenantId,@PathVariable UUID id) {
        StockDisposalResponse response = stockDisposalService.getStockDisposalById(tenantId,id);
        return ResponseEntity.ok(response);
    }

    // Get all disposals
    @GetMapping("{tenantId}/get-all")
    public ResponseEntity<?> getAllStockDisposal(@PathVariable UUID tenantId,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Page<StockDisposalResponse> response = stockDisposalService.getAllStockDisposal(tenantId,page,size);
        return ResponseEntity.ok(response);
    }

    /*
    * update an existing stock disposal
    */
     @PutMapping(value="/{tenantId}/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     public ResponseEntity<?> updateStockDisposal(
             @PathVariable UUID tenantId,
             @PathVariable UUID id,
             @RequestPart("request") StockDisposalRequest request,
             @RequestPart("file") MultipartFile file
     ){
         StockDisposal updated=stockDisposalService.updateStockDisposal(tenantId,id,request,file);

         return ResponseEntity.ok(updated);
     }




    // Delete a stock disposal by ID
    @DeleteMapping("{tenantId}/delete/{id}")
    public ResponseEntity<?> deleteStockDisposal(@PathVariable UUID tenantId ,@PathVariable UUID id) {

        stockDisposalService.deleteStockDisposal(tenantId,id);

        return ResponseEntity.ok("Deleted Successfully");
    }
}
