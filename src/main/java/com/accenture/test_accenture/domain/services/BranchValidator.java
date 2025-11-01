package com.accenture.test_accenture.domain.services;

public class BranchValidator {
    public static void validate(String name, Long franchiseId) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Branch name is required");
        if (franchiseId == null) throw new IllegalArgumentException("FranchiseId is required");
    }
}
