package com.accenture.test_accenture.application.service;

import com.accenture.test_accenture.application.port.in.ProductInPort;
import com.accenture.test_accenture.application.port.out.ProductOutPort;
import com.accenture.test_accenture.domain.Product;
import com.accenture.test_accenture.domain.services.ProductValidator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductUseCase implements ProductInPort {

    private final ProductOutPort productOutPort;

    public ProductUseCase(ProductOutPort productOutPort) {
        this.productOutPort = productOutPort;
    }

    @Override
    public Mono<Product> save(Product product) {
        ProductValidator.validate(product.name(), product.stock(), product.branchId());
        return productOutPort.save(product);
    }

    @Override
    public Flux<Product> findAll() {
        return productOutPort.findAll();
    }

    @Override
    public Mono<Product> updateName(Long id, String name) {
        return productOutPort.updateName(id, name);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return productOutPort.deleteById(id);
    }

    @Override
    public Mono<Product> updateStock(Long id, Integer stock) {
        return productOutPort.updateStock(id, stock);
    }
}
