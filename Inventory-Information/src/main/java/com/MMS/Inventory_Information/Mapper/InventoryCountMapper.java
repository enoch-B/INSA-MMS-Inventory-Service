package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.InventoryCountRequest;
import com.MMS.Inventory_Information.dto.response.InventoryCountDetailResponse;
import com.MMS.Inventory_Information.dto.response.InventoryCountResponse;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InventoryCountMapper {


    public static InventoryCount toEntity(InventoryCountRequest request){
        InventoryCount entity = new InventoryCount();
         entity.setId(UUID.randomUUID());
         entity.setTenantId(request.getTenantId());
            entity.setStoreId(request.getStoreId());
            entity.setInventoryCountNumber(request.getInventoryCountNumber());
            entity.setBudgetYearId(request.getBudgetYearId());
            entity.setCountType(request.getCountType());
            entity.setStoreType(request.getStoreType());
            entity.setCommitteeId(request.getCommitteeId());
            entity.setCommitteeName(request.getCommitteeName());
            entity.setCommitteeMembersId(request.getCommitteeMembersId());
            entity.setCommitteeMembersName(request.getCommitteeMembersName());
            entity.setPreparedBy(request.getPreparedBy());
            entity.setPreparedOn(request.getPreparedOn());
            entity.setCountDate(request.getCountDate());
            if (request.getInventoryItems() != null) {
                List<InventoryDetail> details = request.getInventoryItems().stream().map(itemRequest -> {
                    InventoryDetail detail = new InventoryDetail();
                    detail.setId(UUID.randomUUID());
                    detail.setItemId(itemRequest.getItemId());
                    detail.setQuantity(itemRequest.getQuantity());
                    detail.setRemark(itemRequest.getRemark());
                    detail.setInventoryCount(entity);
                    return detail;
                }).collect(Collectors.toList());
                entity.setInventoryDetails(details);
            }

            return entity;
    }

       public static InventoryCountResponse toResponse(InventoryCount count ){
           InventoryCountResponse response = new InventoryCountResponse();
           response.setId(count.getId());
           response.setTenantId(count.getTenantId());
           response.setInventoryCountNumber(count.getInventoryCountNumber());
           response.setStoreId(count.getStoreId());
           response.setBudgetYearId(count.getBudgetYearId());
           response.setCountType(count.getCountType());
           response.setStoreType(count.getStoreType());
           response.setCommitteeId(count.getCommitteeId());
           response.setCommitteeName(count.getCommitteeName());
           response.setCommitteeMembersId(count.getCommitteeMembersId());
           response.setCommitteeMembersName(count.getCommitteeMembersName());
           response.setPreparedBy(count.getPreparedBy());
           response.setPreparedOn(count.getPreparedOn());
           response.setCountDate(count.getCountDate());
           response.setCreatedAt(count.getCreatedAt());
           response.setUpdatedAt(count.getUpdatedAt());

           if(count.getInventoryDetails() != null) {
               List<InventoryCountDetailResponse> details = count.getInventoryDetails().stream().map(detail ->{
                     InventoryCountDetailResponse detailResponse = new InventoryCountDetailResponse();
                     detailResponse.setId(detail.getId());
                     detailResponse.setItemId(detail.getItemId());
                     detailResponse.setQuantity(detail.getQuantity());
                     detailResponse.setRemark(detail.getRemark());

                     return detailResponse;
               }).collect(Collectors.toList());
                response.setInventoryDetails(details);
           }
              return response;
       }

}
