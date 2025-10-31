package com.accenture.test_accenture.infraestructure.persistence;

import com.accenture.test_accenture.application.port.out.FranchiseOutPort;
import com.accenture.test_accenture.domain.Franchise;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;
import com.accenture.test_accenture.infraestructure.mappers.FranchiseMapper;
import com.accenture.test_accenture.infraestructure.persistence.entities.FranchiseEntity;
import com.accenture.test_accenture.infraestructure.persistence.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranchiseRepositoryAdapter implements FranchiseOutPort {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;

    public FranchiseRepositoryAdapter(FranchiseRepository franchiseRepository, FranchiseMapper franchiseMapper) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseMapper = franchiseMapper;
    }
    @Override
    public Mono<Franchise> save(Franchise franchise) {
        FranchiseEntity franchiseEntity = franchiseMapper.toEntity(franchise);
        return franchiseRepository.save(franchiseEntity)
                .map(franchiseMapper::toDomain);
    }

    @Override
    public Flux<Franchise> findAll() {
        return franchiseRepository.findAll()
                .map(franchiseMapper::toDomain);
    }

    @Override
    public Mono<Franchise> updateName(Long id, String name) {
        return franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Franchise not found by id: "+ id)))
                .flatMap(franchiseEntity -> {
                    franchiseEntity.setName(name);
                    return franchiseRepository.save(franchiseEntity);
                }).map(franchiseMapper::toDomain);
    }
}
