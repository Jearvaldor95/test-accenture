package com.accenture.test_accenture.domain.services;

public class FranchiseValidator {
    public static void validate(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Franchise name is required");
    }
}
