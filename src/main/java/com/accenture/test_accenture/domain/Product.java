package com.accenture.test_accenture.domain;

public record Product(
    Long id,
    String name,
    Integer stock,
    Long branchId
) {
}
