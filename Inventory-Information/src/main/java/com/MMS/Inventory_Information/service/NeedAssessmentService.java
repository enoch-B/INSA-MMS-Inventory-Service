package com.MMS.Inventory_Information.service;


import com.MMS.Inventory_Information.Mapper.InventoryCountMapper;
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
}
