package com.accenture.test_accenture.domain.services;

import com.accenture.test_accenture.domain.Product;

public class ProductValidator {
    public static void validate(String name, Integer stock, Long branchId) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Product name is required");
        if (stock == null || stock < 0) throw new IllegalArgumentException("The stock should not be negative");
        if (branchId == null) throw new IllegalArgumentException("BranchId is required");
    }
}
