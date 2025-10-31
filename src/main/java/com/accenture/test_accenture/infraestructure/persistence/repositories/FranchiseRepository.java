package com.accenture.test_accenture.infraestructure.persistence.repositories;

import com.accenture.test_accenture.infraestructure.persistence.entities.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FranchiseRepository extends ReactiveCrudRepository<FranchiseEntity, Long> {
}
