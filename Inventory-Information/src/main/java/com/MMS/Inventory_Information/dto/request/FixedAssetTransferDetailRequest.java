package com.MMS.Inventory_Information.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class FixedAssetTransferDetailRequest {
        @NotNull
        private UUID itemId; // Reference to Item from item-service

        @NotBlank
        private String tagNumber; // Reference to Tag from fixed-asset-service
        private Double bookValue;
        private String accountCode;

        @NotBlank
        @Positive
        private Integer quantity;

        @NotBlank
        private String remark;

        @NotBlank
        private String description;

}
