package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.LostFixedAssetRequest;
import com.MMS.Inventory_Information.dto.response.LostFixedAssetResponse;
import com.MMS.Inventory_Information.dto.response.LostItemDetailResponse;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostFixedAsset;
import com.MMS.Inventory_Information.model.LostFixedAsset.LostItemDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LostFixedAssetMapper {
     public static LostFixedAsset toEntity(LostFixedAssetRequest request){
         LostFixedAsset entity = new LostFixedAsset();
           entity.setTenantId(request.getTenantId());
           entity.setStoreId(request.getStoreId());
              entity.setDepartmentId(request.getDepartmentId());
              entity.setProcessedById(request.getProcessedById());
              entity.setLostItemNo(request.getLostItemNo());
              entity.setRegistrationDate(request.getRegistrationDate());
              entity.setProcessedBy(request.getProcessedBy());
              entity.setProcessedOn(request.getProcessedOn());
              entity.setFileType(request.getFileType());
              entity.setFileName(request.getFileName());
              entity.setData(request.getData());

              if(request.getLostItemDetails() !=null) {
                  List<LostItemDetail> details = request.getLostItemDetails().stream().map(detailRequest -> {
                      LostItemDetail detail = new LostItemDetail();
                      detail.setItemId(detailRequest.getItemId());
                      detail.setTagNo(detailRequest.getTagNo());
                      detail.setBookValue(detailRequest.getBookValue());
                      detail.setAccountCode(detailRequest.getAccountCode());
                      detail.setDuration(detailRequest.getDuration());
                      detail.setDescription(detailRequest.getDescription());
                      detail.setRemark(detailRequest.getRemark());
                      detail.setLostFixedAsset(entity);
                      return detail;

                  }).collect(Collectors.toList());
                  entity.setLostItemDetails(details);
              }
                return entity;
              }
        public static LostFixedAssetResponse toResponse(LostFixedAsset entity) {
            LostFixedAssetResponse response = new LostFixedAssetResponse();
            response.setId(entity.getId());
            response.setTenantId(entity.getTenantId());
            response.setStoreId(entity.getStoreId());
            response.setDepartmentId(entity.getDepartmentId());
            response.setProcessedById(entity.getProcessedById());
            response.setLostItemNo(entity.getLostItemNo());
            response.setRegistrationDate(entity.getRegistrationDate());
            response.setProcessedBy(entity.getProcessedBy());
            response.setProcessedOn(entity.getProcessedOn());
            response.setFileName(entity.getFileName());
            response.setFileType(entity.getFileType());
            response.setData(entity.getData());
            response.setCreatedAt(entity.getCreatedAt());
            response.setUpdatedAt(entity.getUpdatedAt());

            if (entity.getLostItemDetails() != null) {
                List<LostItemDetailResponse> details = entity.getLostItemDetails().stream().map(detail -> {
                    LostItemDetailResponse detailResponse = new LostItemDetailResponse();
                    detailResponse.setId(detail.getId());
                    detailResponse.setItemId(detail.getItemId());
                    detailResponse.setTagNo(detail.getTagNo());
                    detailResponse.setBookValue(detail.getBookValue());
                    detailResponse.setAccountCode(detail.getAccountCode());
                    detailResponse.setDuration(detail.getDuration());
                    detailResponse.setDescription(detail.getDescription());
                    detailResponse.setRemark(detail.getRemark());
                    return detailResponse;
                }).collect(Collectors.toList());

                response.setLostFixedAssetDetails(details);
            }
            return response;

        }

        public void updateEntity(LostFixedAsset entity, LostFixedAssetRequest request) {
            entity.setStoreId(request.getStoreId());
            entity.setDepartmentId(request.getDepartmentId());
            entity.setProcessedById(request.getProcessedById());
            entity.setLostItemNo(request.getLostItemNo());
            entity.setRegistrationDate(request.getRegistrationDate());
            entity.setProcessedBy(request.getProcessedBy());
            entity.setProcessedOn(request.getProcessedOn());
            entity.setFileType(request.getFileType());
            entity.setFileName(request.getFileName());
            entity.setData(request.getData());

        }

}


