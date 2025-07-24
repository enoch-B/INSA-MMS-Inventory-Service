package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.StockDisposalMapper;
import com.MMS.Inventory_Information.dto.request.StockDisposalRequest;
import com.MMS.Inventory_Information.dto.response.StockDisposalResponse;
import com.MMS.Inventory_Information.model.StockDisposal.StockDisposal;
import com.MMS.Inventory_Information.Repository.StockDisposalRepository;
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
public class StockDisposalService {

    private final StockDisposalRepository stockDisposalRepository;

    /*
   // Format: SDNo-001/2025
 */
    public String generateDisposalNo(UUID tenantId) {
        int currentYear = LocalDate.now().getYear();
        List<String> recentNumbers = stockDisposalRepository.findRecentDisposalNo(tenantId, currentYear);
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
        return String.format("SDNo-%04d/%d", nextNumber, currentYear);
    }


    public StockDisposalResponse addStockDisposal(UUID tenantId, StockDisposalRequest request, MultipartFile file) {
         String disposalNo=generateDisposalNo(tenantId);

        request.setTenantId(tenantId);
        request.setDisposalNo(disposalNo);

        StockDisposal stockDisposal=StockDisposalMapper.toEntity(request);

        try {
            if (file != null && !file.isEmpty()) {
                stockDisposal.setFileName(file.getOriginalFilename());
                stockDisposal.setFileType(file.getContentType());
                stockDisposal.setFileData(file.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }

        StockDisposal savedEntity = stockDisposalRepository.save(stockDisposal);

        return StockDisposalMapper.toResponse(savedEntity);

    }

    public StockDisposalResponse getStockDisposalById(UUID tenantId,UUID id) {
        StockDisposal stockDisposal = stockDisposalRepository.findById(id)
                .filter(sd->sd.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Stock Disposal not found with ID: " + id));

        return StockDisposalMapper.toResponse(stockDisposal);
    }

    public void deleteStockDisposal(UUID id, UUID uuid) {
        StockDisposal existing = stockDisposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock Disposal not found with ID: " + id));
        stockDisposalRepository.delete(existing);
    }


    public Page<StockDisposalResponse> getAllStockDisposal(UUID tenantId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());

        return stockDisposalRepository.findByTenantId(tenantId,pageable)
                .map(StockDisposalMapper::toResponse);
    }

    public StockDisposal updateStockDisposal(UUID tenantId, UUID id, StockDisposalRequest request, MultipartFile file) {
        StockDisposal existing = stockDisposalRepository.findById(id)
                .filter(sd->sd.getTenantId().equals(tenantId))
                .orElseThrow(()-> new RuntimeException("Stock Disposal Not Found"));

        StockDisposalMapper.updateStockDisposal(existing,request,file);

        return  stockDisposalRepository.save(existing);
    }
}
