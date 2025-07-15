package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Repository.InventoryBalanceItemRepository;
import com.MMS.Inventory_Information.Repository.InventoryBalanceRepository;
import com.MMS.Inventory_Information.Repository.InventoryCountDetailRepository;
import com.MMS.Inventory_Information.Repository.InventoryCountRepository;
import com.MMS.Inventory_Information.dto.request.InventoryBalanceRequest;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryDetail;
import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalance;
import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalanceItem;
import lombok.RequiredArgsConstructor;
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


    public void createInventoryBalance(UUID tenantId, InventoryBalanceRequest request) {

        InventoryCount inventoryCount = inventoryCountRepository.findById(request.getInventoryCountId())
                .orElseThrow(() -> new RuntimeException("Inventory Count not found"));

        // Create and populate the InventoryBalance entity
        InventoryBalance inventoryBalance = new InventoryBalance();
        inventoryBalance.setId(UUID.randomUUID());
        inventoryBalance.setTenantId(tenantId);
        inventoryBalance.setInventoryCount(inventoryCount);
        inventoryBalance.setPreparedById(request.getPreparedById());
        inventoryBalance.setStoreType(request.getStoreType());
        inventoryBalance.setPreparedBy(request.getPreparedBy());
        inventoryBalance.setPreparedOn(request.getPreparedOn());

        // Save InventoryBalance first (parent)
        inventoryBalanceRepository.save(inventoryBalance);

        // Copy inventory details from InventoryCount to InventoryBalanceItems
        for (InventoryDetail detail : inventoryCountDetailRepository.findAllByInventoryCountId(inventoryCount.getId())) {
            InventoryBalanceItem balanceItem = new InventoryBalanceItem();
            balanceItem.setId(UUID.randomUUID());
            balanceItem.setItemId(detail.getItemId()); // Reference to Item from item-service
            balanceItem.setItemCode(detail.getItemCode());
            balanceItem.setQuantity(detail.getQuantity());
            balanceItem.setRemark(detail.getRemark());
            balanceItem.setInventoryBalance(inventoryBalance); // set relationship

            // Save child items
            inventoryBalanceItemRepository.save(balanceItem);
        }
    }
}
