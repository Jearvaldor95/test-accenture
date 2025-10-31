package com.accenture.test_accenture.application.port.out;

import com.accenture.test_accenture.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductOutPort {
    Mono<Product> save(Product product);
    Flux<Product> findAll();
    Mono<Product> updateName(Long id, String name);
    Mono<Void> deleteById(Long id);
    Mono<Product> updateStock(Long id, Integer stock);
}
