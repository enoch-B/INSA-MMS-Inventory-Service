package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalDetailRequest;
import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalDetailResponse;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalResponse;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposal;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;
import com.MMS.Inventory_Information.model.FixedAssetTransfer.FixedAssetTransferDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FixedAssetDisposalMapper {
         public static FixedAssetDisposal toEntity(FixedAssetDisposalRequest request) {
             FixedAssetDisposal entity = new FixedAssetDisposal();
             entity.setId(UUID.randomUUID());
             entity.setTenantId(request.getTenantId());
             entity.setStoreId(request.getStoreId());
             entity.setProcessedById(request.getProcessedById());
             entity.setFA_disposalNo(request.getFA_disposalNo());
             entity.setProcessedBy(request.getProcessedBy());
             entity.setApprovedDate(request.getApprovedDate());
             entity.setDisposalStatus(request.getDisposalStatus());
             entity.setProcessedOn(request.getProcessedOn());
             entity.setProposedDate(request.getProposedDate());
             entity.setFileName(request.getFileName());
             entity.setFileType(request.getFileType());
             entity.setFileData(request.getFileData());

             if (request.getDisposalDetails() != null) {
                 List<FixedAssetDisposalDetail> details = request.getDisposalDetails().stream().map(detailRequest -> {
                     FixedAssetDisposalDetail detail = new FixedAssetDisposalDetail();
                     detail.setId(UUID.randomUUID());
                     detail.setItemId(detailRequest.getItemId());
                     detail.setTagNumber(detailRequest.getTagNumber());
                     detail.setGainLossValueId(detailRequest.getGainLossValueId());
                     detail.setSellingPriceId(detailRequest.getSellingPriceId());
                     detail.setAccountCode(detailRequest.getAccountCode());
                     detail.setBookValue(detailRequest.getBookValue());
                     detail.setItemLocation(detailRequest.getItemLocation());
                     detail.setDisposalMethod(detailRequest.getDisposalMethod());
                     detail.setFixedAssetDisposal(entity);
                     return detail;

                 }).collect(Collectors.toList());
                    entity.setDisposalDetails(details);
             }
           return entity;
         }

         public static FixedAssetDisposalResponse toResponse(FixedAssetDisposal entity) {
             FixedAssetDisposalResponse response = new FixedAssetDisposalResponse();
                     response.setId(entity.getId());
                     response.setTenantId(entity.getTenantId());
                     response.setStoreId(entity.getStoreId());
                     response.setProcessedById(entity.getProcessedById());
                     response.setProcessedBy(entity.getProcessedBy());
                     response.setFA_disposalNo(entity.getFA_disposalNo());
                     response.setDisposalStatus(entity.getDisposalStatus());
                     response.setProcessedOn(entity.getProcessedOn());
                     response.setProposedDate(entity.getProposedDate());
                     response.setApprovedDate(entity.getApprovedDate());
                     response.setFileName(entity.getFileName());
                     response.setFileType(entity.getFileType());
                     response.setFileData(entity.getFileData());
              if(entity.getDisposalDetails() != null) {
                  List<FixedAssetDisposalDetailResponse> details = entity.getDisposalDetails().stream().map(detail -> {
                      FixedAssetDisposalDetailResponse detailResponse = new FixedAssetDisposalDetailResponse();
                        detailResponse.setId(detail.getId());
                        detailResponse.setItemId(detail.getItemId());
                        detailResponse.setTagNumber(detail.getTagNumber());
                        detailResponse.setGainLossValueId(detail.getGainLossValueId());
                        detailResponse.setSellingPriceId(detail.getSellingPriceId());
                        detailResponse.setAccountCode(detail.getAccountCode());
                        detailResponse.setBookValue(detail.getBookValue());
                        detailResponse.setItemLocation(detail.getItemLocation());
                        detailResponse.setDisposalMethod(detail.getDisposalMethod());

                        return detailResponse;
                  }).collect(Collectors.toList());

                    response.setDisposalDetails(details);

              }
              return response;

         }
         // Update entity with request data
    public static void updateEntity(FixedAssetDisposal entity, FixedAssetDisposalRequest request) {
        entity.setStoreId(request.getStoreId());
        entity.setProcessedById(request.getProcessedById());
        entity.setProcessedBy(request.getProcessedBy());
        entity.setApprovedDate(request.getApprovedDate());
        entity.setDisposalStatus(request.getDisposalStatus());
        entity.setProcessedOn(request.getProcessedOn());
        entity.setProposedDate(request.getProposedDate());
        entity.setFileName(request.getFileName());
        entity.setFileType(request.getFileType());
        entity.setFileData(request.getFileData());
        }
    }


