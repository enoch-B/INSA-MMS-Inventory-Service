package com.MMS.Inventory_Information.Mapper;


import com.MMS.Inventory_Information.dto.request.LostStockItemRequest;
import com.MMS.Inventory_Information.dto.response.LostItemDetailResponse;
import com.MMS.Inventory_Information.dto.response.LostStockItemDetailResponse;
import com.MMS.Inventory_Information.dto.response.LostStockItemResponse;
import com.MMS.Inventory_Information.model.LostStockItem.LostStockItem;
import com.MMS.Inventory_Information.model.LostStockItem.LostStockItemDetail;

import java.util.List;
import java.util.stream.Collectors;

public class LostStockItemMapper {

    public static LostStockItem toEntity(LostStockItemRequest lostStockItemRequest){
        LostStockItem entity = new LostStockItem();
        entity.setTenantId(lostStockItemRequest.getTenantId());
        entity.setLostStockItemNo(lostStockItemRequest.getLostStockItemNo());
        entity.setRegionId(lostStockItemRequest.getRegionId());
        entity.setStoreId(lostStockItemRequest.getStoreId());
        entity.setDepartmentId(lostStockItemRequest.getDepartmentId());
        entity.setStatus(lostStockItemRequest.getStatus());
        entity.setProcessedBy(lostStockItemRequest.getProcessedBy());
        entity.setProcessedById(lostStockItemRequest.getProcessedById());
        entity.setProcessedOn(lostStockItemRequest.getProcessedOn());
        entity.setRegistrationDate(lostStockItemRequest.getRegistrationDate());
        entity.setCommitteeId(lostStockItemRequest.getCommitteeId());
        entity.setCommitteeMembersId(lostStockItemRequest.getCommitteeMembersId());
        entity.setCommitteeName(lostStockItemRequest.getCommitteeName());
        entity.setCommitteeMembersName(lostStockItemRequest.getCommitteeMembersName());
        entity.setFileType(lostStockItemRequest.getFileType());
        entity.setFileName(lostStockItemRequest.getFileName());
        entity.setData(lostStockItemRequest.getData());

        if(entity.getLostStockItemDetails() != null){
            List<LostStockItemDetail> details = lostStockItemRequest.getLostStockItemDetailRequest().stream().map(detailRequest ->{
                LostStockItemDetail detail = new LostStockItemDetail();

                detail.setItemId(detailRequest.getItemId());
                detail.setDuration(detailRequest.getDuration());
                detail.setDescription(detailRequest.getDescription());
                detail.setQuantity(detailRequest.getQuantity());
                detail.setRemark(detailRequest.getRemark());
                detail.setLostStockItem(entity);

                return detail;
            }).collect((Collectors.toList()));

            entity.setLostStockItemDetails(details);
        }

        return entity;

    }

    public static LostStockItemResponse toResponse(LostStockItem lostStockItem){
        LostStockItemResponse response= new LostStockItemResponse();
        response.setId(lostStockItem.getId());
        response.setTenantId(lostStockItem.getTenantId());
        response.setLostStockItemNo(lostStockItem.getLostStockItemNo());
        response.setRegionId(lostStockItem.getRegionId());
        response.setStoreId(lostStockItem.getStoreId());
        response.setDepartmentId(lostStockItem.getDepartmentId());
        response.setStatus(lostStockItem.getStatus());
        response.setProcessedBy(lostStockItem.getProcessedBy());
        response.setProcessedById(lostStockItem.getProcessedById());
        response.setProcessedOn(lostStockItem.getProcessedOn());
        response.setRegistrationDate(lostStockItem.getRegistrationDate());
        response.setCommitteeId(lostStockItem.getCommitteeId());
        response.setCommitteeMembersId(lostStockItem.getCommitteeMembersId());
        response.setCommitteeName(lostStockItem.getCommitteeName());
        response.setCommitteeMembersName(lostStockItem.getCommitteeMembersName());
        response.setCreatedAt(lostStockItem.getCreatedAt());
        response.setUpdatedAt(lostStockItem.getUpdatedAt());
        response.setFileType(lostStockItem.getFileType());
        response.setFileName(lostStockItem.getFileName());
        response.setData(lostStockItem.getData());

        if(response.getLostStockItemDetailResponses() != null){
            List<LostStockItemDetailResponse>  details=lostStockItem.getLostStockItemDetails().stream().map(lostStockItemDetail -> {
                LostStockItemDetailResponse detailResponse = new LostStockItemDetailResponse();
                detailResponse.setId(lostStockItemDetail.getId());
                detailResponse.setItemId(lostStockItemDetail.getItemId());
                detailResponse.setDuration(lostStockItemDetail.getDuration());
                detailResponse.setDescription(lostStockItemDetail.getDescription());
                detailResponse.setQuantity(lostStockItemDetail.getQuantity());
                detailResponse.setRemark(lostStockItemDetail.getRemark());

                return detailResponse;

            }).collect(Collectors.toList());

            response.setLostStockItemDetailResponses(details);
        }

        return response;
    }


}
