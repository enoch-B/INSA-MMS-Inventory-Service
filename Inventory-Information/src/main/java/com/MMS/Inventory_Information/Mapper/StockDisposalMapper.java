package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.StockDisposalRequest;
import com.MMS.Inventory_Information.dto.response.StockDisposalResponse;
import com.MMS.Inventory_Information.dto.response.StockDisposalDetailResponse;
import com.MMS.Inventory_Information.model.StockDisposal.StockDisposal;
import com.MMS.Inventory_Information.model.StockDisposal.StockDisposalDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class StockDisposalMapper {

    public static StockDisposal toEntity(StockDisposalRequest request) {
        StockDisposal entity = new StockDisposal();
        entity.setTenantId(request.getTenantId());
        entity.setStoreId(request.getStoreId());
        entity.setProcessedById(request.getProcessedById());
        entity.setProcessedBy(request.getProcessedBy());
        entity.setDisposalNo(request.getDisposalNo());
        entity.setDisposalStatus(request.getDisposalStatus());
        entity.setProposeDate(request.getProposeDate());
        entity.setApprovedDate(request.getApprovedDate());
        entity.setProposedOn(request.getProposedOn());
        entity.setFileName(request.getFileName());
        entity.setFileType(request.getFileType());
        entity.setFileData(request.getFileData());

        if (request.getStockDisposalDetails() != null) {
            List<StockDisposalDetail> details = request.getStockDisposalDetails().stream().map(detailRequest -> {
                StockDisposalDetail detail = new StockDisposalDetail();
                detail.setItemId(detailRequest.getItemId());
                detail.setDisposalMethod(detailRequest.getDisposalMethod());
                detail.setDescription(detailRequest.getDescription());
                detail.setSellingPrice(detailRequest.getSellingPrice());
                detail.setExpirationDate(detailRequest.getExpirationDate());
                detail.setStockDisposal(entity);
                return detail;
            }).collect(Collectors.toList());

            entity.setStockDisposalDetails(details);
        }

        return entity;
    }

    public static StockDisposalResponse toResponse(StockDisposal entity) {
        StockDisposalResponse response = new StockDisposalResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setStoreId(entity.getStoreId());
        response.setProcessedById(entity.getProcessedById());
        response.setProcessedBy(entity.getProcessedBy());
        response.setDisposalNo(entity.getDisposalNo());
        response.setDisposalStatus(entity.getDisposalStatus());
        response.setProposeDate(entity.getProposeDate());
        response.setApprovedDate(entity.getApprovedDate());
        response.setProposedOn(entity.getProposedOn());
        response.setFileName(entity.getFileName());
        response.setFileType(entity.getFileType());
        response.setFileData(entity.getFileData());

        if (entity.getStockDisposalDetails() != null) {
            List<StockDisposalDetailResponse> details = entity.getStockDisposalDetails().stream().map(detail -> {
                StockDisposalDetailResponse detailResponse = new StockDisposalDetailResponse();
                detailResponse.setId(detail.getId());
                detailResponse.setItemId(detail.getItemId());
                detailResponse.setDisposalMethod(detail.getDisposalMethod());
                detailResponse.setDescription(detail.getDescription());
                detailResponse.setSellingPrice(detail.getSellingPrice());
                detailResponse.setExpirationDate(detail.getExpirationDate());
                return detailResponse;
            }).collect(Collectors.toList());

            response.setStockDisposalDetails(details);
        }

        return response;
    }

    public static void updateStockDisposal(StockDisposal existing, StockDisposalRequest request, MultipartFile file){
        existing.setTenantId(request.getTenantId());
        existing.setStoreId(request.getStoreId());
        existing.setProcessedById(request.getProcessedById());
        existing.setProcessedBy(request.getProcessedBy());
        existing.setDisposalStatus(request.getDisposalStatus());
        existing.setProposeDate(request.getProposeDate());
        existing.setApprovedDate(request.getApprovedDate());
        existing.setProposedOn(request.getProposedOn());
        existing.setFileName(request.getFileName());
        existing.setFileType(request.getFileType());
        existing.setFileData(request.getFileData());

            List<StockDisposalDetail> details = request.getStockDisposalDetails().stream().map(detailRequest -> {
                StockDisposalDetail detail = new StockDisposalDetail();
                detail.setItemId(detailRequest.getItemId());
                detail.setDisposalMethod(detailRequest.getDisposalMethod());
                detail.setDescription(detailRequest.getDescription());
                detail.setSellingPrice(detailRequest.getSellingPrice());
                detail.setExpirationDate(detailRequest.getExpirationDate());
                detail.setStockDisposal(existing);
                return detail;
            }).toList();

            existing.getStockDisposalDetails().clear();
            existing.getStockDisposalDetails().addAll(details);


        }
}
