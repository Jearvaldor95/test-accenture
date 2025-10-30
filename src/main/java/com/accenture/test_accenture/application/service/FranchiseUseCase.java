package com.accenture.test_accenture.application.service;

import com.accenture.test_accenture.application.port.in.FranchiseInPort;
import com.accenture.test_accenture.application.port.out.FranchiseOutPort;
import com.accenture.test_accenture.domain.Franchise;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranchiseUseCase implements FranchiseInPort {

    private final FranchiseOutPort franchiseOutPort;

    public FranchiseUseCase(FranchiseOutPort franchiseOutPort) {
        this.franchiseOutPort = franchiseOutPort;
    }
    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return franchiseOutPort.save(franchise);
    }

    @Override
    public Flux<Franchise> findAll() {
        return franchiseOutPort.findAll();
    }

    @Override
    public Mono<Franchise> updateName(Long id, String name) {
        return franchiseOutPort.updateName(id, name);
    }
}
