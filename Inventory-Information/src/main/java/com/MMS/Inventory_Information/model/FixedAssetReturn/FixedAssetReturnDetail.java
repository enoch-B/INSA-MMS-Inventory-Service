package com.MMS.Inventory_Information.model.FixedAssetReturn;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Lookup;

import java.util.UUID;

@Entity
@Data
@Table(name = "fixed_asset_return_details")
public class FixedAssetReturnDetail {

      @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private UUID id;


    private UUID itemInfoId; // from another service
    private  UUID fixedAssetId; // from the fixed asset

    @Column(nullable = false)
    private String itemStatus; //



    private String itemName; // snapshot from another service
     private String bookValue; // snapshot from another service
     private String accountCode; // snapshot from another service
    private String tagNumber; // snapshot from another service

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fixed_asset_return_id")
    private FixedAssetReturn fixedAssetReturn; // the return this detail belongs to
}
