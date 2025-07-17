package com.MMS.Inventory_Information.service;

import com.MMS.Inventory_Information.Mapper.StockDisposalMapper;
import com.MMS.Inventory_Information.dto.request.StockDisposalRequest;
import com.MMS.Inventory_Information.dto.response.StockDisposalResponse;
import com.MMS.Inventory_Information.model.StockDisposal.StockDisposal;
import com.MMS.Inventory_Information.Repository.StockDisposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockDisposalService {

    private final StockDisposalRepository stockDisposalRepository;

    public StockDisposalResponse create(StockDisposalRequest request) {
        StockDisposal entity = StockDisposalMapper.toEntity(request);
        StockDisposal saved = stockDisposalRepository.save(entity);
        return StockDisposalMapper.toResponse(saved);
    }

    public StockDisposalResponse getById(UUID id) {
        StockDisposal entity = stockDisposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock Disposal not found with ID: " + id));
        return StockDisposalMapper.toResponse(entity);
    }

    public List<StockDisposalResponse> getAll() {
        return stockDisposalRepository.findAll()
                .stream()
                .map(StockDisposalMapper::toResponse)
                .collect(Collectors.toList());
    }

    public StockDisposalResponse update(UUID id, StockDisposalRequest request) {
        StockDisposal existing = stockDisposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock Disposal not found with ID: " + id));

        // Map incoming request to entity, preserving the existing ID
        StockDisposal updated = StockDisposalMapper.toEntity(request);
        updated.setId(existing.getId());

        StockDisposal saved = stockDisposalRepository.save(updated);
        return StockDisposalMapper.toResponse(saved);
    }

    public void delete(UUID id) {
        StockDisposal existing = stockDisposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock Disposal not found with ID: " + id));
        stockDisposalRepository.delete(existing);
    }
}
