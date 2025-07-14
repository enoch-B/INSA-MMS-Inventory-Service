package com.MMS.Inventory_Information.enums;

public enum DisposalStatus {
    // Initial state when disposal request is created
    PENDING_APPROVAL,

    // When manager approves the disposal
    APPROVED,

    // When disposal is rejected
    REJECTED,

    // When asset is scheduled for disposal
    SCHEDULED_FOR_DISPOSAL,

    // When disposal process is complete
    DISPOSED,

    // If disposal needs to be cancelled
    CANCELLED,

    // For assets that need special handling
    PENDING_AUDIT,

    // For assets that cannot be disposed normally
    ON_HOLD
}
