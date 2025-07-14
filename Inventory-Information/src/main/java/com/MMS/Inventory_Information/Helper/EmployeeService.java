package com.MMS.Inventory_Information.Helper;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeService {
    // Mock data — in a real app, fetch from employee-service
    private static final Map<UUID, String> MOCK_EMPLOYEES = new HashMap<>();

    static {
        MOCK_EMPLOYEES.put(UUID.fromString("a1a1a1a1-a1a1-a1a1-a1a1-a1a1a1a1a1a1"), "Henok Birhanu");
        MOCK_EMPLOYEES.put(UUID.fromString("b2b2b2b2-b2b2-b2b2-b2b2-b2b2b2b2b2b2"), "Sara Teshome");
        MOCK_EMPLOYEES.put(UUID.fromString("c3c3c3c3-c3c3-c3c3-c3c3-c3c3c3c3c3c3"), "Abebe Kebede");
    }

    public List<String> getEmployeeNamesByIds(List<UUID> ids) {
        List<String> names = new ArrayList<>();
        for (UUID id : ids) {
            names.add(MOCK_EMPLOYEES.getOrDefault(id, "Unknown Employee"));
        }
        return names;
    }
}
