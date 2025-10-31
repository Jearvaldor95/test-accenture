package com.accenture.test_accenture.infraestructure.persistence;

import com.accenture.test_accenture.application.port.out.BranchOutPort;
import com.accenture.test_accenture.domain.Branch;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;
import com.accenture.test_accenture.infraestructure.mappers.BranchMapper;
import com.accenture.test_accenture.infraestructure.persistence.entities.BranchEntity;
import com.accenture.test_accenture.infraestructure.persistence.repositories.BranchRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BranchRepositoryAdapter implements BranchOutPort {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchRepositoryAdapter(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }
    @Override
    public Mono<Branch> save(Branch branch) {
        BranchEntity branchEntity = branchMapper.toEntity(branch);
        return branchRepository.save(branchEntity)
                .map(branchMapper::toDomain);
    }

    @Override
    public Flux<Branch> findAll() {
        return branchRepository.findAll()
                .map(branchMapper::toDomain);
    }

    @Override
    public Mono<Branch> updateName(Long id, String name) {
        return branchRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Branch not found by id: "+ id)))
                .flatMap(branchEntity -> {
                    branchEntity.setName(name);
                    return branchRepository.save(branchEntity);
                }).map(branchMapper::toDomain);
    }
}
