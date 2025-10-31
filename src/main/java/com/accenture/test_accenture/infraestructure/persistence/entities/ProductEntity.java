package com.accenture.test_accenture.infraestructure.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "products")
public class ProductEntity {

    @Id
    private Long id;
    private String name;
    private Integer stock;
    @Column("id_branch")
    private Long branchId;
}
