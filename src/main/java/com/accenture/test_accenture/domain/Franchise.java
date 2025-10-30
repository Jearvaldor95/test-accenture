package com.accenture.test_accenture.domain;

import java.util.List;

public record Franchise(
    Long id,
    String name,
    List<Branch> branches
) {
}
