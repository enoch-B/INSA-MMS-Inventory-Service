package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Mapper.LostStockItemMapper;
import com.MMS.Inventory_Information.Repository.LostStockItemDetailRepository;
import com.MMS.Inventory_Information.Repository.LostStockItemRepository;
import com.MMS.Inventory_Information.dto.request.LostStockItemRequest;
import com.MMS.Inventory_Information.dto.response.LostStockItemResponse;
import com.MMS.Inventory_Information.model.LostStockItem.LostStockItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LostStockItemService {

      private final LostStockItemRepository lostStockItemRepository;
      private final LostStockItemDetailRepository lostStockItemDetailRepository;

        /*
           // Format: StockLostItemNo-001/2025
         */
    public String generateLostStockItemNo(UUID tenantId) {
        int currentYear = LocalDate.now().getYear();
        List<String> recentNumbers = lostStockItemRepository.findRecentLostStockItemNo(tenantId, currentYear);
        int nextNumber = 1;

        if (!recentNumbers.isEmpty()) {
            try {
                String latest = recentNumbers.get(0); // e.g., "StockLostItemNo-003/2025"
                String numberPart = latest.split("-")[1].split("/")[0]; // "003"
                nextNumber = Integer.parseInt(numberPart) + 1;
            } catch (Exception e) {
                nextNumber = 1;
            }
        }
        return String.format("StockLostItemNo-%03d/%d", nextNumber, currentYear);
    }

    public LostStockItemResponse addLostStockItem(UUID tenantId, LostStockItemRequest request, MultipartFile file) {
            String lostItemNo = generateLostStockItemNo(tenantId);

        //set the generated lost stock item nuber and tenant id
        request.setTenantId(tenantId);
        request.setLostStockItemNo(lostItemNo);

        LostStockItem lostStockItem = LostStockItemMapper.toEntity(request);

        try {
            if (file != null && !file.isEmpty()) {
                lostStockItem.setFileName(file.getOriginalFilename());
                lostStockItem.setFileType(file.getContentType());
                lostStockItem.setData(file.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }

        //save the entity to repository
        LostStockItem savedEntity = lostStockItemRepository.save(lostStockItem);

        // return the response Dto
        return  LostStockItemMapper.toResponse(savedEntity);
    }

    /*
       Get all lost stock item paginated
     */
    public Page<LostStockItemResponse> getAllLostStockItem(UUID tenantId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());

        return lostStockItemRepository.findByTenantId(tenantId,pageable)
                .map(LostStockItemMapper::toResponse);
    }

    public LostStockItemResponse getLostStockItemById(UUID tenantId, UUID id) {
        LostStockItem lostStockItem = lostStockItemRepository.findById(id)
                .filter(si -> si.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("LostStock Item Not Found or tenant Mismatch"));

        return LostStockItemMapper.toResponse(lostStockItem);
    }

   /*
        delete lost stock item
    */
    public void deleteLostStockItem(UUID tenantId, UUID id) {

        LostStockItem lostStockItem= lostStockItemRepository.findById(id)
                .filter(si -> si.getTenantId().equals(tenantId))
                .orElseThrow( () -> new RuntimeException("Item Not found or tenant mismatch"));

        lostStockItemRepository.delete(lostStockItem);
    }

    /*
       update logic
     */

    public LostStockItem updateLostStockItem(UUID id, UUID tenantId, LostStockItemRequest request, MultipartFile file) {
        LostStockItem existing = lostStockItemRepository.findById(id)
                .filter(si -> si.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("LostStockItem not found"));

        LostStockItemMapper.updateLostStockItemFromRequest(request, file, existing);

        return lostStockItemRepository.save(existing);
    }
}
