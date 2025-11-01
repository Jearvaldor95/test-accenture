package com.accenture.test_accenture.domain;

import java.util.List;

public record Branch(
    Long id,
    String name,
    Long franchiseId,
    List<Product> products
) {
}
