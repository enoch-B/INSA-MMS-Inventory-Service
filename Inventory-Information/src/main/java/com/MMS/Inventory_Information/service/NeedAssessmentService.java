package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Mapper.NeedAssessmentMapper;
import com.MMS.Inventory_Information.Repository.NeedAssessmentDetailRepository;
import com.MMS.Inventory_Information.Repository.NeedAssessmentRepository;
import com.MMS.Inventory_Information.dto.request.NeedAssessmentRequest;
import com.MMS.Inventory_Information.dto.response.NeedAssessmentResponse;
import com.MMS.Inventory_Information.model.NeedAssessment.NeedAssessment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NeedAssessmentService {

    private final NeedAssessmentRepository needAssessmentRepository;
    private final NeedAssessmentDetailRepository needAssessmentDetailRepository;

    public NeedAssessmentResponse addNeedAssessment(UUID tenantId, NeedAssessmentRequest needAssessmentRequest) {

         needAssessmentRequest.setTenantId(tenantId);

        NeedAssessment needAssessment = NeedAssessmentMapper.toEntity(needAssessmentRequest);
         NeedAssessment savedAssessment = needAssessmentRepository.save(needAssessment);

         return NeedAssessmentMapper.toResponse(savedAssessment);

    }

      /*
        Get paginated Need Assessment
       */
    public Page<NeedAssessmentResponse>getAllNeedAssessmentPaginated(UUID tenantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return needAssessmentRepository.findByTenantId(tenantId, pageable)
                .map(NeedAssessmentMapper::toResponse);
    }

    /*
     *  Get single Need Assessment By id
     */

    public NeedAssessmentResponse getNeedAssessmentById(UUID tenantId, UUID id) {
        NeedAssessment needAssessment=needAssessmentRepository.findById(id)
                .filter(na-> na.getTenantId().equals(tenantId))
                .orElseThrow(()-> new RuntimeException("Need Assessment Not Found Or Tenant Mismatch"));
        return NeedAssessmentMapper.toResponse(needAssessment);
    }


    public void deleteNeedAssessment(UUID tenantId, UUID id) {

        NeedAssessment needAssessment = needAssessmentRepository.findById(id)
                .filter(na-> na.getTenantId().equals(tenantId))
                .orElseThrow(()-> new RuntimeException("Need Assessment Not Found or Tenant Mismatch"));

        needAssessmentRepository.delete(needAssessment);
    }

    public NeedAssessmentResponse updateNeedAssessment(UUID tenantId, UUID id, NeedAssessmentRequest request) {
        NeedAssessment existing = needAssessmentRepository.findById(id)
                .filter(na->na.getTenantId().equals(tenantId))
                .orElseThrow(()-> new RuntimeException("Need Assessment Not Found by this id" + id));

        NeedAssessmentMapper.updateNeedAssessmentFromRequest(request,existing);

         NeedAssessment saved=needAssessmentRepository.save(existing);

         return NeedAssessmentMapper.toResponse(saved);
    }
}
