package com.accenture.test_accenture.application.port.in;

import com.accenture.test_accenture.domain.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseInPort {

    Mono<Franchise> save(Franchise franchise);
    Flux<Franchise> findAll();
    Mono<Franchise> updateName(Long id, String name);
}
