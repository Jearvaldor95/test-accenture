package com.accenture.test_accenture.infraestructure.persistence.repositories;

import com.accenture.test_accenture.infraestructure.persistence.entities.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
}
