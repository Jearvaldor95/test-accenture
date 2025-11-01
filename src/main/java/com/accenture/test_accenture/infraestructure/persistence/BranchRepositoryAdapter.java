package com.accenture.test_accenture.infraestructure.persistence;

import com.accenture.test_accenture.application.port.out.BranchOutPort;
import com.accenture.test_accenture.domain.Branch;
import com.accenture.test_accenture.domain.Product;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;
import com.accenture.test_accenture.infraestructure.mappers.BranchMapper;
import com.accenture.test_accenture.infraestructure.persistence.entities.BranchEntity;
import com.accenture.test_accenture.infraestructure.persistence.repositories.BranchRepository;
import com.accenture.test_accenture.infraestructure.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BranchRepositoryAdapter implements BranchOutPort {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final ProductRepository productRepository;

    public BranchRepositoryAdapter(BranchRepository branchRepository, BranchMapper branchMapper,
                                   ProductRepository productRepository) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.productRepository = productRepository;
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
                .flatMap(branchEntity ->
                    productRepository.findByBranchId(branchEntity.getId())
                            .map(productEntity -> new Product(
                                    productEntity.getId(),
                                    productEntity.getName(),
                                    productEntity.getStock(),
                                    productEntity.getBranchId()
                            ))
                            .collectList()
                            .map(products -> new Branch(
                                    branchEntity.getId(),
                                    branchEntity.getName(),
                                    branchEntity.getFranchiseId(),
                                    products
                            ))
                );
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
