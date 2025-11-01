package com.accenture.test_accenture.infraestructure.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchResponse {
    private Long id;
    private String name;
    private Long franchiseId;
}
