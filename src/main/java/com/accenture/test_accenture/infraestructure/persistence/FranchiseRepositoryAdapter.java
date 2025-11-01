package com.accenture.test_accenture.infraestructure.persistence;

import com.accenture.test_accenture.application.port.ProductBranch;
import com.accenture.test_accenture.application.port.out.FranchiseOutPort;
import com.accenture.test_accenture.domain.Franchise;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;
import com.accenture.test_accenture.infraestructure.mappers.FranchiseMapper;
import com.accenture.test_accenture.infraestructure.persistence.entities.FranchiseEntity;
import com.accenture.test_accenture.infraestructure.persistence.repositories.BranchRepository;
import com.accenture.test_accenture.infraestructure.persistence.repositories.FranchiseRepository;
import com.accenture.test_accenture.infraestructure.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranchiseRepositoryAdapter implements FranchiseOutPort {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public FranchiseRepositoryAdapter(FranchiseRepository franchiseRepository, FranchiseMapper franchiseMapper,
                                      ProductRepository productoRepository,
                                      BranchRepository branchRepository) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseMapper = franchiseMapper;
        this.productRepository = productoRepository;
        this.branchRepository = branchRepository;
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

    @Override
    public Flux<ProductBranch> findProductWithMaxStockPerBranchForFranchise(Long franchiseId) {
        return branchRepository.findAll()
                .filter(branch -> branch.getFranchiseId().equals(franchiseId))
                .flatMap(branch ->
                        productRepository.findAll()
                                .filter(product -> product.getBranchId().equals(branch.getId()))
                                .reduce((p1, p2) -> p1.getStock() >= p2.getStock() ? p1 : p2)
                                .flatMapMany(maxProduct ->
                                        productRepository.findAll()
                                                .filter(p -> p.getBranchId().equals(branch.getId()) && p.getStock().equals(maxProduct.getStock()))
                                                .map(p -> new ProductBranch(
                                                        p.getId(),
                                                        p.getName(),
                                                        p.getStock(),
                                                        branch.getId(),
                                                        branch.getName()
                                                ))
                                )
                );
    }


}
