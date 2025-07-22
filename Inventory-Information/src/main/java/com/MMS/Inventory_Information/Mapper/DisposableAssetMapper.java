package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.DisposableAssetRequest;
import com.MMS.Inventory_Information.dto.response.DisposableAssetResponse;
import com.MMS.Inventory_Information.dto.response.DisposableFixedAssetDetailResponse;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableAsset;
import com.MMS.Inventory_Information.model.DisposalCollection.DisposableFixedAssetDetail;

import java.util.List;
import java.util.stream.Collectors;

public class DisposableAssetMapper {

    public static DisposableAsset toEntity(DisposableAssetRequest request){
        DisposableAsset entity = new DisposableAsset();

        entity.setTenantId(request.getTenantId());
        entity.setDisposableType(request.getDisposableType());
        entity.setDisposalStatus(request.getDisposalStatus());
        entity.setDrNo(request.getDrNo());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setStoreId(request.getStoreId());
        entity.setProcessedById(request.getProcessedById());
        entity.setProcessedByName(request.getProcessedByName());
        entity.setRequisitionDate(request.getRequisitionDate());
        entity.setProcessedOn(request.getProcessedOn());

        if(request.getDisposableFixedAssetDetails() != null){
            List<DisposableFixedAssetDetail> details = request.getDisposableFixedAssetDetails().stream().map(detailRequest ->{
                DisposableFixedAssetDetail detail = new DisposableFixedAssetDetail();
                detail.setItemId(detailRequest.getItemId());
                detail.setAccountCode(detailRequest.getAccountCode());
                detail.setTagNumber(detailRequest.getTagNumber());
                detail.setBatchNo(detailRequest.getBatchNo());
                detail.setBookValue(detailRequest.getBookValue());
                detail.setDescription(detailRequest.getDescription());
                detail.setExpirationDate(detailRequest.getExpirationDate());
                detail.setQuantity(detailRequest.getQuantity());
                detail.setDisposableAsset(entity);

                return detail;

            }).collect(Collectors.toList());
            entity.setDisposableAssetDetail(details);

        }
        return entity;

    }

         //Map the entity to ResponseDto

    public static DisposableAssetResponse toResponse(DisposableAsset entity){
         DisposableAssetResponse response = new DisposableAssetResponse();
         response.setId(entity.getId());
         response.setTenantId(entity.getTenantId());
         response.setStoreId(entity.getStoreId());
         response.setDisposableType(entity.getDisposableType());
         response.setDisposalStatus(entity.getDisposalStatus());
         response.setDrNo(entity.getDrNo());
         response.setProcessedById(entity.getProcessedById());
         response.setProcessedByName(entity.getProcessedByName());
         response.setProcessedOn(entity.getProcessedOn());
         response.setDepartmentId(entity.getDepartmentId());
         response.setCreatedAt(entity.getCreatedAt());
         response.setUpdatedAt(entity.getUpdatedAt());

         if(entity.getDisposableAssetDetail() != null){
             List<DisposableFixedAssetDetailResponse> details= entity.getDisposableAssetDetail().stream().map(disposableFixedAssetDetail -> {
                 DisposableFixedAssetDetailResponse detailResponse = new DisposableFixedAssetDetailResponse();
                  detailResponse.setId(disposableFixedAssetDetail.getId());
                  detailResponse.setItemId(disposableFixedAssetDetail.getItemId());
                  detailResponse.setTagNumber(disposableFixedAssetDetail.getTagNumber());
                  detailResponse.setBookValue(disposableFixedAssetDetail.getBookValue());
                  detailResponse.setAccountCode(disposableFixedAssetDetail.getAccountCode());
                  detailResponse.setBatchNo(disposableFixedAssetDetail.getBatchNo());
                  detailResponse.setDescription(disposableFixedAssetDetail.getDescription());
                  detailResponse.setQuantity(disposableFixedAssetDetail.getQuantity());
                  detailResponse.setExpirationDate(disposableFixedAssetDetail.getExpirationDate());

                  return detailResponse;

             }).collect(Collectors.toList());

             response.setDisposableFixedAssetDetails(details);

         }
         return response;



    }
}
