package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.request.LostStockItemRequest;
import com.MMS.Inventory_Information.dto.response.LostStockItemResponse;
import com.MMS.Inventory_Information.service.LostStockItemService;
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
public class LostStockItemController {
    private final LostStockItemService lostStockItemService;

    @PostMapping(value="/{tenantId}/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LostStockItemResponse> addLostStockItem(@PathVariable UUID tenantId,  @RequestPart("request") LostStockItemRequest request,
                                                                  @RequestPart(value = "file", required = false) MultipartFile file){

        LostStockItemResponse response= lostStockItemService.addLostStockItem(tenantId,request,file);

        return ResponseEntity.ok(response);

    }
    /*
      Get all LostStockItems Paginated
     */
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<LostStockItemResponse>> getAllLostStockItem(@PathVariable UUID tenantId,@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size){
        Page<LostStockItemResponse> response= lostStockItemService.getAllLostStockItem(tenantId,page,size);

        return ResponseEntity.ok(response);
    }

    /*
       get lost stock item by id
     */
    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<LostStockItemResponse> getLostStockItemById(@PathVariable UUID tenantId,@PathVariable UUID id){
        LostStockItemResponse response= lostStockItemService.getLostStockItemById(tenantId,id);

        return ResponseEntity.ok(response);
    }



    /*
       Delete logic
     */
    @DeleteMapping("/{tenantId}/delete/{id}")
    public ResponseEntity<String> deleteLostStockItem(@PathVariable UUID tenantId,@PathVariable UUID id){
        lostStockItemService.deleteLostStockItem(tenantId,id);

        return ResponseEntity.ok("Deleted Successfully")
    }

}

