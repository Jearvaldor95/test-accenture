package com.accenture.test_accenture.application.port.out;

import com.accenture.test_accenture.application.port.ProductBranch;
import com.accenture.test_accenture.domain.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseOutPort {
    Mono<Franchise> save(Franchise franchise);
    Flux<Franchise> findAll();
    Mono<Franchise> updateName(Long id, String name);
    Flux<ProductBranch> findProductWithMaxStockPerBranchForFranchise(Long franchiseId);
}
