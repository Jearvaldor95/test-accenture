package com.accenture.test_accenture.application.service;

import com.accenture.test_accenture.application.port.in.BranchInPort;
import com.accenture.test_accenture.application.port.out.BranchOutPort;
import com.accenture.test_accenture.domain.Branch;
import com.accenture.test_accenture.domain.services.BranchValidator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BranchUseCase implements BranchInPort {

    private final BranchOutPort branchOutPort;

    public BranchUseCase(BranchOutPort branchOutPort) {
        this.branchOutPort = branchOutPort;
    }
    @Override
    public Mono<Branch> save(Branch branch) {
        BranchValidator.validate(branch.name(), branch.franchiseId());
        return branchOutPort.save(branch);
    }

    @Override
    public Flux<Branch> findAll() {
        return branchOutPort.findAll();
    }

    @Override
    public Mono<Branch> updateName(Long id, String name) {
        return branchOutPort.updateName(id, name);
    }
}
