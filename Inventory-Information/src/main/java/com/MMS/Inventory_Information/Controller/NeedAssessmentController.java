package com.MMS.Inventory_Information.Controller;


import com.MMS.Inventory_Information.dto.request.NeedAssessmentRequest;
import com.MMS.Inventory_Information.dto.response.NeedAssessmentResponse;
import com.MMS.Inventory_Information.model.NeedAssessment.NeedAssessment;
import com.MMS.Inventory_Information.service.NeedAssessmentService;
import feign.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory/Need-Assessment/")
public class NeedAssessmentController {
    private final NeedAssessmentService needAssessmentService;

    @PostMapping("/{tenantId}/add")
    public ResponseEntity<NeedAssessmentResponse> addNeedAssessment(@PathVariable UUID tenantId, @RequestBody @Valid NeedAssessmentRequest needAssessmentRequest) {
        //set tenant id

        NeedAssessmentResponse response = needAssessmentService.addNeedAssessment(tenantId, needAssessmentRequest);

        return ResponseEntity.ok(response);
    }

    // Suggested simpler path: /{tenantId}/get-all for paginated get-all
    @GetMapping("/{tenantId}/get-all")
    public ResponseEntity<Page<NeedAssessmentResponse>> getAllNeedAssessmentPaginated(
            @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<NeedAssessmentResponse> response = needAssessmentService.getAllNeedAssessmentPaginated(tenantId,page,size);

        return ResponseEntity.ok(response);
    }
    /*
       get single Need Assessment by id
     */
    @GetMapping("/{tenantId}/get/{id}")
    public ResponseEntity<?> getNeedAssessmentById(@PathVariable UUID tenantId, @PathVariable UUID id){
        NeedAssessmentResponse response= needAssessmentService.getNeedAssessmentById(tenantId,id);

        return ResponseEntity.ok(response);
    }

    /*
       delete logic
     */
     @DeleteMapping("/{tenantId}/delete/{id}")
    public  ResponseEntity<?> deleteNeedAssessment(@PathVariable UUID tenantId, @PathVariable UUID id){
         needAssessmentService.deleteNeedAssessment(tenantId,id);

         return ResponseEntity.ok("Deleted Successfully");
     }

     /*
       * update by ID
      */
    @PutMapping("/{tenantId}/update/{id}")
    public ResponseEntity<?> updateNeedAssessment(@PathVariable UUID tenantId, @PathVariable UUID id,
                                                  @RequestBody @Valid NeedAssessmentRequest request ){
        NeedAssessmentResponse updated= needAssessmentService.updateNeedAssessment(tenantId,id,request);

        return ResponseEntity.ok(updated);
    }

}