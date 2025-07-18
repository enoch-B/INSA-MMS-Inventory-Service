package com.MMS.Inventory_Information.mapper;

import com.MMS.Inventory_Information.dto.response.InventoryCountDetailResponse;
import com.MMS.Inventory_Information.dto.response.InventoryCountResponse;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryCount;
import com.MMS.Inventory_Information.model.InventoryCountSheet.InventoryDetail;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryCountMapper {

    public static InventoryCountDetailResponse toDetailResponse(InventoryDetail detail) {
        InventoryCountDetailResponse response = new InventoryCountDetailResponse();
        response.setId(detail.getId());
        response.setItemId(detail.getItemId());
        response.setItemCode(detail.getItemCode());
        response.setQuantity(detail.getQuantity());
        response.setRemark(detail.getRemark());
        return response;
    }

    public static InventoryCountResponse toResponse(InventoryCount count) {
        InventoryCountResponse response = new InventoryCountResponse();
        response.setId(count.getId());
        response.setTenantId(count.getTenantId());
        response.setInventoryCountNumber(count.getInventoryCountNumber());
        response.setStoreId(count.getStoreId());
        response.setBudgetYear(count.getBudgetYear());
        response.setCountType(count.getCountType());
        response.setStoreType(count.getStoreType());
        response.setCommitteeId(count.getCommitteeId());
        response.setCommitteeName(count.getCommitteeName());
        response.setCommitteeMemberIds(count.getCommitteeMemberIds());
        response.setCommitteeMemberName(count.getCommitteeMemberName());
        response.setPreparedBy(count.getPreparedBy());
        response.setPreparedOn(count.getPreparedOn());
        response.setCountDate(count.getCountDate());
        response.setCreatedAt(count.getCreatedAt());
        response.setUpdatedAt(count.getUpdatedAt());

        List<InventoryCountDetailResponse> detailResponses = count.getInventoryDetails()
                .stream()
                .map(InventoryCountMapper::toDetailResponse)
                .collect(Collectors.toList());

        response.setInventoryDetails(detailResponses);
        return response;
    }
}
