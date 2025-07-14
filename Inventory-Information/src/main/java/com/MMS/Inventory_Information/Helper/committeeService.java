package com.MMS.Inventory_Information.Helper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class committeeService {
    private static final Map<UUID, String> MOCK_COMMITTEES = new HashMap<>();

    static {
        MOCK_COMMITTEES.put(UUID.fromString("11111111-1111-1111-1111-111111111111"), "Inventory Committee A");
        MOCK_COMMITTEES.put(UUID.fromString("22222222-2222-2222-2222-222222222222"), "Fixed Asset Committee");
    }

    public String getCommitteeNameById(UUID committeeId) {
        return MOCK_COMMITTEES.getOrDefault(committeeId, "Unknown Committee");
    }

}
