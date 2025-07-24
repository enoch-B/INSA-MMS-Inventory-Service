package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.FixedAssetTransferRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferDetailResponse;
import com.MMS.Inventory_Information.dto.response.FixedAssetTransferResponse;
import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransfer;
import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransferDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FixedAssetTransferMapper {
   public static FixedAssetTransfer toEntity(FixedAssetTransferRequest request){
       FixedAssetTransfer entity = new FixedAssetTransfer();
            entity.setTenantId(request.getTenantId());
            entity.setTransferNo(request.getTransferNo());
            entity.setEmployeeId(request.getEmployeeId());
            entity.setDepartmentId(request.getDepartmentId());
            entity.setTransferToId(request.getTransferToId());
            entity.setTransferFromId(request.getTransferFromId());
            entity.setProcessedBy(request.getProcessedBy());
            entity.setPreparedById(request.getPreparedById());
            entity.setProcessedOn(request.getProcessedOn());
            entity.setTransferType(request.getTransferType());
     if(request.getTransferDetails() != null){
         List<FixedAssetTransferDetail> details = request.getTransferDetails().stream().map(detailReq ->{
                FixedAssetTransferDetail detail = new FixedAssetTransferDetail();
                detail.setItemId(detailReq.getItemId());
                detail.setTagNumber(detailReq.getTagNumber());
                detail.setBookValue(detailReq.getBookValue());
                detail.setAccountCode(detailReq.getAccountCode());
                detail.setQuantity(detailReq.getQuantity());
                detail.setRemark(detailReq.getRemark());
                detail.setDescription(detailReq.getDescription());
                detail.setFixedAssetTransfer(entity);
                return detail;
         }).collect(Collectors.toList());

            entity.setTransferDetails(details);

       }
       return  entity;


   }
   public static FixedAssetTransferResponse toResponse(FixedAssetTransfer entity){
         FixedAssetTransferResponse response = new FixedAssetTransferResponse();
         response.setId(entity.getId());
         response.setTenantId(entity.getTenantId());
         response.setEmployeeId(entity.getEmployeeId());
         response.setDepartmentId(entity.getDepartmentId());
         response.setTransferToId(entity.getTransferToId());
         response.setTransferFromId(entity.getTransferFromId());
         response.setTransferNo(entity.getTransferNo());
         response.setProcessedBy(entity.getProcessedBy());
         response.setPreparedById(entity.getPreparedById());
         response.setProcessedOn(entity.getProcessedOn());
         response.setTransferType(entity.getTransferType());
         response.setCreatedAt(entity.getCreatedAt());
         response.setUpdatedAt(entity.getUpdatedAt());

         if (entity.getTransferDetails() != null) {
              List<FixedAssetTransferDetailResponse> details = entity.getTransferDetails().stream()
                     .map(detail -> {
                          FixedAssetTransferDetailResponse detailResponse = new FixedAssetTransferDetailResponse();
                          detailResponse.setId(detail.getId());
                          detailResponse.setItemId(detail.getItemId());
                          detailResponse.setTagNumber(detail.getTagNumber());
                          detailResponse.setBookValue(detail.getBookValue());
                          detailResponse.setAccountCode(detail.getAccountCode());
                          detailResponse.setQuantity(detail.getQuantity());
                          detailResponse.setRemark(detail.getRemark());
                          detailResponse.setDescription(detail.getDescription());
                          return detailResponse;
                     }).collect(Collectors.toList());

              response.setTransferDetails(details);
         }

         return response;
   }
    // for updating an existing entity
   public static void updateEntity(FixedAssetTransfer entity, FixedAssetTransferRequest request) {
       entity.setEmployeeId(request.getEmployeeId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setPreparedById(request.getPreparedById());
        entity.setTransferType(request.getTransferType());
        entity.setTransferFromId(request.getTransferFromId());
        entity.setTransferToId(request.getTransferToId());
        entity.setProcessedBy(request.getProcessedBy());
        entity.setProcessedOn(request.getProcessedOn());
        // Note: You do NOT update transferNo, createdAt, etc.
       List<FixedAssetTransferDetail> details = request.getTransferDetails().stream().map(detailReq ->{
           FixedAssetTransferDetail detail = new FixedAssetTransferDetail();
           detail.setItemId(detailReq.getItemId());
           detail.setTagNumber(detailReq.getTagNumber());
           detail.setBookValue(detailReq.getBookValue());
           detail.setAccountCode(detailReq.getAccountCode());
           detail.setQuantity(detailReq.getQuantity());
           detail.setRemark(detailReq.getRemark());
           detail.setDescription(detailReq.getDescription());
           detail.setFixedAssetTransfer(entity);
           return detail;
       }).toList();

       entity.getTransferDetails().clear();
       entity.getTransferDetails().addAll(details);
    }



}
