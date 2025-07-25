package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Mapper.InventoryBalanceMapper;
import com.MMS.Inventory_Information.Repository.InventoryBalanceItemRepository;
import com.MMS.Inventory_Information.Repository.InventoryBalanceRepository;
import com.MMS.Inventory_Information.Repository.InventoryCountDetailRepository;
import com.MMS.Inventory_Information.Repository.InventoryCountRepository;
import com.MMS.Inventory_Information.dto.request.InventoryBalanceRequest;
import com.MMS.Inventory_Information.dto.response.InventoryBalanceResponse;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryDetail;
import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalance;
import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalanceItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryBalanceService {
    // This service is responsible for handling inventory balance operations.
    public final InventoryBalanceRepository inventoryBalanceRepository;
    public final InventoryCountDetailRepository inventoryCountDetailRepository;
    public final InventoryCountRepository inventoryCountRepository;
    public final InventoryBalanceItemRepository inventoryBalanceItemRepository;


    public InventoryBalanceResponse createInventoryBalance(UUID tenantId, InventoryBalanceRequest request) {

        InventoryCount inventoryCount = inventoryCountRepository.findById(request.getInventoryCountId())
                .orElseThrow(() -> new RuntimeException("Inventory Count not found"));

        InventoryBalance balance = InventoryBalanceMapper.mapToEntity(request,inventoryCount);
        InventoryBalance saved = inventoryBalanceRepository.save(balance);

        return InventoryBalanceMapper.mapToResponse(saved);
        }


    public Page<InventoryBalanceResponse> getAllInventoryBalance(UUID tenantId, int page, int size) {
            Pageable pageable=PageRequest.of(page,size, Sort.by("createdAt").descending());

            return inventoryBalanceRepository.findByTenantId(tenantId,pageable)
                    .map(InventoryBalanceMapper::mapToResponse);

    }

    public InventoryBalanceResponse getInventoryBalanceById(UUID tenantId, UUID id) {
        InventoryBalance inventoryBalance=inventoryBalanceRepository.findById(id)
                .filter(ib->ib.getTenantId().equals(tenantId))
                .orElseThrow(()->new RuntimeException("Item Not Found Or Tenant Mismatch" +id));

        return  InventoryBalanceMapper.mapToResponse(inventoryBalance);
    }

    public void deleteInventoryBalance(UUID tenantId, UUID id) {
        InventoryBalance inventoryBalance=inventoryBalanceRepository.findById(id)
                .filter(ib->ib.getTenantId().equals(tenantId))
                .orElseThrow(()->new RuntimeException("Item Not Found Or Tenant Mismatch"));

        inventoryBalanceRepository.delete(inventoryBalance);
    }
}

