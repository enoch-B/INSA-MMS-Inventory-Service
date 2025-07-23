package com.MMS.Inventory_Information.Mapper;

import com.MMS.Inventory_Information.dto.request.NeedAssessmentRequest;
import com.MMS.Inventory_Information.dto.response.NeedAssessmentDetailResponse;
import com.MMS.Inventory_Information.dto.response.NeedAssessmentResponse;
import com.MMS.Inventory_Information.model.NeedAssessment.NeedAssessment;
import com.MMS.Inventory_Information.model.NeedAssessment.NeedAssessmentDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NeedAssessmentMapper {

    public static NeedAssessment toEntity(NeedAssessmentRequest request){
        NeedAssessment entity = new NeedAssessment();
        entity.setTenantId(request.getTenantId());
        entity.setStoreId(request.getStoreId());
        entity.setPurchaseType(request.getPurchaseType());
        entity.setBudgetYearId(request.getBudgetYearId());
        entity.setDepartmentId(request.getDepartmentId());

        if(request.getAssessmentDetail() != null) {
            List<NeedAssessmentDetail> details = request.getAssessmentDetail().stream().map(detailRequest ->{
                NeedAssessmentDetail detail = new NeedAssessmentDetail();
                detail.setItemId(detailRequest.getItemId());
                detail.setGeneralLedgerId(detailRequest.getGeneralLedger());
                detail.setBudgetAmount(detailRequest.getBudgetAmount());
                detail.setNeedAssessment(entity);


                return detail;
            }).collect(Collectors.toList());

            entity.setNeedAssessmentDetails(details);
        }
        return entity;

        }

        public static NeedAssessmentResponse toResponse(NeedAssessment assessment){
           NeedAssessmentResponse response = new NeedAssessmentResponse();
           response.setId(assessment.getId());
           response.setTenantId(assessment.getTenantId());
           response.setDepartmentId(assessment.getDepartmentId());
           response.setStoreId(assessment.getStoreId());
           response.setPurchaseType(assessment.getPurchaseType());
           response.setBudgetYearId(assessment.getBudgetYearId());
           response.setCreatedAt(assessment.getCreatedAt());
           response.setUpdatedAt(assessment.getUpdatedAt());

           if(assessment.getNeedAssessmentDetails() != null){
               List<NeedAssessmentDetailResponse> details = assessment.getNeedAssessmentDetails().stream().map(detail ->{
                   NeedAssessmentDetailResponse detailResponse = new NeedAssessmentDetailResponse();
                           detailResponse.setId(detail.getId());
                           detailResponse.setItemId(detail.getItemId());
                           detailResponse.setGeneralLedger(detail.getGeneralLedgerId());
                           detailResponse.setBudgetAmount(detail.getBudgetAmount());

                           return detailResponse;
               }).collect(Collectors.toList());

                  response.setAssessmentResponse(details);
           }

         return response;
        }


        // Handle Update

    public static  void updateNeedAssessmentFromRequest(NeedAssessmentRequest request, NeedAssessment entity){
        entity.setTenantId(request.getTenantId());
        entity.setStoreId(request.getStoreId());
        entity.setPurchaseType(request.getPurchaseType());
        entity.setBudgetYearId(request.getBudgetYearId());
        entity.setDepartmentId(request.getDepartmentId());

        List<NeedAssessmentDetail> details = request.getAssessmentDetail().stream().map(detailRequest ->{
            NeedAssessmentDetail detail = new NeedAssessmentDetail();
            detail.setItemId(detailRequest.getItemId());
            detail.setGeneralLedgerId(detailRequest.getGeneralLedger());
            detail.setBudgetAmount(detailRequest.getBudgetAmount());
            detail.setNeedAssessment(entity);


            return detail;
        }).toList();
       entity.getNeedAssessmentDetails().clear();
       entity.getNeedAssessmentDetails().addAll(details);
    }



    }

