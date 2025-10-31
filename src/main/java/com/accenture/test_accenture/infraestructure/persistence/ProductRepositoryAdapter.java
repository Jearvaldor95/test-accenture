package com.accenture.test_accenture.infraestructure.persistence;

import com.accenture.test_accenture.application.port.out.ProductOutPort;
import com.accenture.test_accenture.domain.Product;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;
import com.accenture.test_accenture.infraestructure.mappers.ProductMapper;
import com.accenture.test_accenture.infraestructure.persistence.entities.ProductEntity;
import com.accenture.test_accenture.infraestructure.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductRepositoryAdapter implements ProductOutPort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Override
    public Mono<Product> save(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        return productRepository.save(productEntity)
                .map(productMapper::toDomain);
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll()
                .map(productMapper::toDomain);
    }

    @Override
    public Mono<Product> updateName(Long id, String name) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found by id: "+ id)))
                .flatMap(productEntity -> {
                    productEntity.setName(name);
                    return productRepository.save(productEntity);
                }).map(productMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found by id: "+ id)))
                .flatMap(productRepository::delete);
    }

    @Override
    public Mono<Product> updateStock(Long id, Integer stock) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found by id: "+ id)))
                .flatMap(productEntity -> {
                    productEntity.setStock(stock);
                    return productRepository.save(productEntity);
                }).map(productMapper::toDomain);
    }
}
