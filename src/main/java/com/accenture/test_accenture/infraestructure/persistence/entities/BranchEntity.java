package com.accenture.test_accenture.infraestructure.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

@Data
@Table("branches")
public class BranchEntity {

    @Id
    private Long id;
    private String name;
    @Column("id_franchise")
    private Long franchiseId;

}
