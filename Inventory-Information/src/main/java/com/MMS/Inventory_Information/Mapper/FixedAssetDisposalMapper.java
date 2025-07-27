package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.FixedAssetDisposalRequest;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalDetailResponse;
import com.MMS.Inventory_Information.dto.response.FixedAssetDisposalResponse;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposal;
import com.MMS.Inventory_Information.model.FixedAssetDisposal.FixedAssetDisposalDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FixedAssetDisposalMapper {
         public static FixedAssetDisposal toEntity(FixedAssetDisposalRequest request) {
             FixedAssetDisposal entity = new FixedAssetDisposal();
             entity.setTenantId(request.getTenantId());
             entity.setStoreId(request.getStoreId());
             entity.setProcessedById(request.getProcessedById());
             entity.setFixedAssetDisposalNo(request.getFixedAssetDisposalNo());
             entity.setProcessedBy(request.getProcessedBy());
             entity.setApprovedDate(request.getApprovedDate());
             entity.setProcessedOn(request.getProcessedOn());
             entity.setProposedDate(request.getProposedDate());



             if (request.getDisposalDetails() != null) {
                 List<FixedAssetDisposalDetail> details = request.getDisposalDetails().stream().map(detailRequest -> {
                     FixedAssetDisposalDetail detail = new FixedAssetDisposalDetail();

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

                 }).collect(toList());
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
                     response.setFixedAssetDisposalNo(entity.getFixedAssetDisposalNo());
                     response.setProcessedOn(entity.getProcessedOn());
                     response.setProposedDate(entity.getProposedDate());
                     response.setApprovedDate(entity.getApprovedDate());
                     response.setFileName(entity.getFileName());
                     response.setFileType(entity.getFileType());
                     response.setFileData(entity.getFileData());

             // Map values from DisposableAsset
             if (entity.getDisposableAsset() != null) {
                 response.setDisposableAssetId(entity.getDisposableAsset().getId());
                 response.setDisposableNo(entity.getDisposableAsset().getDrNo());
                 response.setDisposalStatus(entity.getDisposableAsset().getDisposalStatus());
             }

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
                  }).collect(toList());

                    response.setDisposalDetailResponses(details);

              }
              return response;

         }
         // Update entity with request data
    public static void updateEntity(FixedAssetDisposal entity, FixedAssetDisposalRequest request) {
        entity.setStoreId(request.getStoreId());
        entity.setProcessedById(request.getProcessedById());
        entity.setProcessedBy(request.getProcessedBy());
        entity.setApprovedDate(request.getApprovedDate());
        entity.setProcessedOn(request.getProcessedOn());
        entity.setProposedDate(request.getProposedDate());

        List<FixedAssetDisposalDetail> details = request.getDisposalDetails().stream().map(detailRequest -> {
            FixedAssetDisposalDetail detail = new FixedAssetDisposalDetail();

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

        }).toList();
          entity.getDisposalDetails().clear();
          entity.getDisposalDetails().addAll(details);
        }
    }


