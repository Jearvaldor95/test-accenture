package com.accenture.test_accenture.infraestructure.persistence.repositories;

import com.accenture.test_accenture.infraestructure.persistence.entities.BranchEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BranchRepository extends ReactiveCrudRepository<BranchEntity, Long> {
}
