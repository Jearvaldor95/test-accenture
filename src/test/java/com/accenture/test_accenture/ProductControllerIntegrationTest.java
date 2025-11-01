package com.accenture.test_accenture;

import com.accenture.test_accenture.application.port.in.ProductInPort;
import com.accenture.test_accenture.domain.Product;
import com.accenture.test_accenture.infraestructure.web.controllers.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
public class ProductControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockitoBean
    private ProductInPort productInPort;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Laptop", 15, 1L);
    }

    @Test
    void createProduct() {
        Mockito.when(productInPort.save(any())).thenReturn(Mono.just(product));

        webTestClient.post().uri("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Laptop")
                .jsonPath("$.stock").isEqualTo(15);
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product(1L, "Laptop", 15, 1L);
        Product product2 = new Product(2L, "Phone", 30, 1L);
        Mockito.when(productInPort.findAll()).thenReturn(Flux.just(product1, product2));

        webTestClient.get().uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Laptop")
                .jsonPath("$[1].id").isEqualTo(2)
                .jsonPath("$[1].name").isEqualTo("Phone");
    }

    @Test
    void updateNameProduct() {
        Product updatedProduct = new Product(1L, "Phone 2", 20, 1L);

        Mockito.when(productInPort.updateName(eq(1L), eq("Phone 2")))
                .thenReturn(Mono.just(updatedProduct));

        webTestClient.put().uri("/api/products/1/name?name=Phone 2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Phone 2");

    }

    @Test
    void deleteProduct() {
        Mockito.when(productInPort.deleteById(1L)).thenReturn(Mono.empty());

        webTestClient.delete().uri("/api/products/1")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void updateStockProduct() {
        Product updatedProduct = new Product(1L, "Phone", 37, 1L);

        Mockito.when(productInPort.updateStock(eq(1L), eq(37)))
                .thenReturn(Mono.just(updatedProduct));

        webTestClient.put().uri("/api/products/1/stock?stock=37")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.stock").isEqualTo(37);

    }




}
