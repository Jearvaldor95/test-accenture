package com.accenture.test_accenture.infraestructure.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

@Data
@Table("franchises")
public class FranchiseEntity {
    @Id
    private Long id;
    private String name;
}
