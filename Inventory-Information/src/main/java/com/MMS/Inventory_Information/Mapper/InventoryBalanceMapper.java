package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.InventoryBalanceRequest;
import com.MMS.Inventory_Information.dto.response.InventoryBalanceItemResponse;
import com.MMS.Inventory_Information.dto.response.InventoryBalanceResponse;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalance;
import com.MMS.Inventory_Information.model.inventoryBalanceSheet.InventoryBalanceItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryBalanceMapper {

    public static InventoryBalance mapToEntity(InventoryBalanceRequest request, InventoryCount count) {
        InventoryBalance entity = new InventoryBalance();
        entity.setTenantId(request.getTenantId());
        entity.setInventoryCount(count);
        entity.setPreparedById(request.getPreparedById());
        entity.setStoreType(request.getStoreType());
        entity.setPreparedOn(request.getPreparedOn());
        entity.setPreparedBy(request.getPreparedBy());

        if(request.getInventoryBalanceItemRequest() != null){
            List<InventoryBalanceItem> details = request.getInventoryBalanceItemRequest().stream().map(itemRequest->{
                InventoryBalanceItem item=new InventoryBalanceItem();
                item.setItemId(itemRequest.getItemId());
                item.setBinBalance(itemRequest.getBinBalance());
                item.setQuantity(itemRequest.getQuantity());

                //  Safe calculation using to get the difference
                BigDecimal diff = itemRequest.getQuantity().subtract(itemRequest.getBinBalance());
                item.setDifference(diff);

                item.setRemark(itemRequest.getRemark());
                item.setInventoryBalance(entity);

                return item;
            }).collect(Collectors.toList());

            entity.setInventoryBalanceItem(details);
        }
                return entity;
    }

     public static InventoryBalanceResponse mapToResponse(InventoryBalance entity){
        InventoryBalanceResponse response = new InventoryBalanceResponse();

         response.setId(entity.getId());
         response.setTenantId(entity.getTenantId());
         response.setInventoryCountId(entity.getInventoryCount().getId());
         response.setPreparedById(entity.getPreparedById());
         response.setPreparedBy(entity.getPreparedBy());
         response.setPreparedOn(entity.getPreparedOn());
         response.setStoreType(entity.getStoreType());
         response.setCreatedAt(entity.getCreatedAt());
         response.setUpdatedAt(entity.getUpdatedAt());

         List<InventoryBalanceItemResponse> itemResponse = entity.getInventoryBalanceItem().stream().map(item -> {
             InventoryBalanceItemResponse ir = new InventoryBalanceItemResponse();
             ir.setId(item.getId());
             ir.setItemId(item.getItemId());
             ir.setQuantity(item.getQuantity());
             ir.setBinBalance(item.getBinBalance());
             ir.setDifference(item.getDifference());
             ir.setRemark(item.getRemark());
             return ir;
         }).collect(Collectors.toList());

         response.setInventoryBalanceItemResponse(itemResponse);

         return response;
     }

 }


