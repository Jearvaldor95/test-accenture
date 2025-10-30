package com.accenture.test_accenture.application.port.out;

import com.accenture.test_accenture.domain.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchOutPort {
    Mono<Branch> save(Branch branch);
    Flux<Branch> findAll();
    Mono<Branch> updateName(Long id, String name);
}
