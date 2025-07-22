package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.FixedAssetReturnRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetReturnDetailResponse;
import com.MMS.Inventory_Information.dto.response.FixedAssetReturnResponse;
import com.MMS.Inventory_Information.model.FixedAssetReturn.FixedAssetReturn;
import com.MMS.Inventory_Information.model.FixedAssetReturn.FixedAssetReturnDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FixedAssetReturnMapper {

    public static FixedAssetReturn toEntity(FixedAssetReturnRequest request) {
        FixedAssetReturn entity = new FixedAssetReturn();
        entity.setTenantId(request.getTenantId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setStoreId(request.getStoreId());
        entity.setProcessedById(request.getProcessedById());
        entity.setReturnedById(request.getReturnedById());
        entity.setProcessedBy(request.getProcessedBy());
        entity.setReturnedBy(request.getReturnedBy());
        entity.setStatus(request.getStatus());
        entity.setReceivedDate(request.getReceivedDate());
        entity.setReturnedDate(request.getReturnedDate());
        entity.setProcessedOn(request.getProcessedOn());

        if (request.getReturnDetails() != null) {
            List<FixedAssetReturnDetail> details = request.getReturnDetails().stream().map(detailReq -> {
                FixedAssetReturnDetail detail = new FixedAssetReturnDetail();
                detail.setItemId(detailReq.getItemId());
                detail.setItemStatus(detailReq.getItemStatus());
                detail.setBookValue(detailReq.getBookValue());
                detail.setAccountCode(detailReq.getAccountCode());
                detail.setTagNumber(detailReq.getTagNumber());
                detail.setDescription(detailReq.getDescription());
                detail.setFixedAssetReturn(entity); // Link to parent
                return detail;
            }).collect(Collectors.toList());
            entity.setReturnDetails(details);
        }

        return entity;
    }

    public static FixedAssetReturnResponse toResponse(FixedAssetReturn entity) {
        FixedAssetReturnResponse response = new FixedAssetReturnResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setStoreId(entity.getStoreId());
        response.setProcessedById(entity.getProcessedById());
        response.setReturnedById(entity.getReturnedById());
        response.setProcessedBy(entity.getProcessedBy());
        response.setReturnedBy(entity.getReturnedBy());
        response.setStatus(entity.getStatus());
        response.setReceivedDate(entity.getReceivedDate());
        response.setReturnedDate(entity.getReturnedDate());
        response.setProcessedOn(entity.getProcessedOn());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getReturnDetails() != null) {
            List<FixedAssetReturnDetailResponse> detailResponses = entity.getReturnDetails().stream()
                    .map(detail -> {
                        FixedAssetReturnDetailResponse d = new FixedAssetReturnDetailResponse();
                        d.setItemId(detail.getItemId());
                        d.setItemStatus(detail.getItemStatus());
                        d.setBookValue(detail.getBookValue());
                        d.setAccountCode(detail.getAccountCode());
                        d.setTagNumber(detail.getTagNumber());
                        d.setDescription(detail.getDescription());
                        return d;
                    }).collect(Collectors.toList());
            response.setReturnDetails(detailResponses);
        }

        return response;
    }
}
