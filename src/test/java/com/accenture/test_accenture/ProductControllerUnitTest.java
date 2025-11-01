package com.accenture.test_accenture;

import com.accenture.test_accenture.application.port.in.ProductInPort;
import com.accenture.test_accenture.domain.Product;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;
import com.accenture.test_accenture.infraestructure.web.controllers.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductControllerUnitTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductInPort productInPort;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Phone", 20, 1L);
    }

    @Test
    void createProduct() {
        Mockito.when(productInPort.save(product)).thenReturn(Mono.just(product));

        StepVerifier.create(productController.saveProduct(product))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void getAllProducts() {
        Product product2 = new Product(2L, "Tablet", 15, 1L);
        Mockito.when(productInPort.findAll()).thenReturn(Flux.just(product, product2));

        StepVerifier.create(productController.getAllProducts())
                .expectNext(product)
                .expectNext(product2)
                .verifyComplete();
    }

    @Test
    void updateProductName() {
        Product updated = new Product(1L, "Phone X", 20, 1L);
        Mockito.when(productInPort.updateName(1L, "Phone X")).thenReturn(Mono.just(updated));

        StepVerifier.create(productController.updateProductName(1L, "Phone X"))
                .expectNext(updated)
                .verifyComplete();
    }

    @Test
    void updateProductStock() {
        Product updated = new Product(1L, "Phone", 37, 1L);
        Mockito.when(productInPort.updateStock(1L, 37)).thenReturn(Mono.just(updated));

        StepVerifier.create(productController.updateProductStock(1L, 37))
                .expectNext(updated)
                .verifyComplete();
    }

    @Test
    void updateStock() {
        Mockito.when(productInPort.updateStock(2L, 99))
                .thenReturn(Mono.error(new NotFoundException("Product not found")));

        StepVerifier.create(productController.updateProductStock(2L, 99))
                .expectErrorMatches(ex -> ex instanceof NotFoundException &&
                        ex.getMessage().equals("Product not found"))
                .verify();
    }
}
