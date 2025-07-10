package com.MMS.Inventory_Information.model.FixedAssetReturn;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Lookup;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "fixed_asset_return_details")
public class FixedAssetReturnDetail {

      @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private UUID id;


    private UUID itemsId; // from another service
    private  UUID fixedAssetId; // from the fixed asset
    private UUID tagId; // from the fixed asset service
    @Column(nullable = false)
    private String itemStatus; //



    private String itemName; // snapshot from another service
     private String bookValue; // snapshot from another service
     private String accountCode; // snapshot from another service
    private String tagNumber; // snapshot from fixed asset service

    @Column(nullable = false)
    private String description;

/*     //audit logs
//    private String createdAt;
   private String updatedAt;
     @PrePersist
      private void onCreate() {
        this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now();}
   */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fixed_asset_return_id")
    private FixedAssetReturn fixedAssetReturn; // the return this detail belongs to
}
