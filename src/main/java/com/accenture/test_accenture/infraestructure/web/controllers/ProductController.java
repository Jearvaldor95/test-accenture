package com.accenture.test_accenture.infraestructure.web.controllers;

import com.accenture.test_accenture.application.port.in.ProductInPort;
import com.accenture.test_accenture.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductInPort productInPort;

    public ProductController(ProductInPort productInPort) {
        this.productInPort = productInPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Product product) {
        return productInPort.save(product);
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return productInPort.findAll();
    }

    @PutMapping("/{id}/name")
    public Mono<Product> updateProductName(@PathVariable("id") Long productId, @RequestParam String name) {
        return productInPort.updateName(productId, name);
    }

    @PutMapping("/{id}/stock")
    public Mono<Product> updateProductStock(@PathVariable("id") Long productId, @RequestParam Integer stock) {
        return productInPort.updateStock(productId, stock);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable("id") Long productId) {
        return productInPort.deleteById(productId);
    }
}
