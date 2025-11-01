package com.accenture.test_accenture;

import com.accenture.test_accenture.application.port.in.BranchInPort;
import com.accenture.test_accenture.domain.Branch;
import com.accenture.test_accenture.domain.Product;
import com.accenture.test_accenture.infraestructure.mappers.BranchMapper;
import com.accenture.test_accenture.infraestructure.web.controllers.BranchController;
import com.accenture.test_accenture.infraestructure.web.dtos.BranchResponse;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class BranchControllerUnitTest {

    @InjectMocks
    private BranchController branchController;

    @Mock
    private BranchInPort branchInPort;

    @Mock
    private BranchMapper branchMapper;

    private Branch branch;
    private BranchResponse branchResponse;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = List.of(
                new Product(1L, "Product A", 10, 1L),
                new Product(2L, "Product B", 2, 1L)
        );

        branch = new Branch(1L, "Main Branch", 1L, products);
        branchResponse = new BranchResponse(branch.id(), branch.name(), branch.franchiseId());
    }

    @Test
    void saveBranch() {
        Mockito.when(branchInPort.save(branch)).thenReturn(Mono.just(branch));
        Mockito.when(branchMapper.toResponse(branch)).thenReturn(branchResponse);

        StepVerifier.create(branchController.saveBranch(branch))
                .expectNext(branchResponse)
                .verifyComplete();
    }

    @Test
    void getAllBranches() {
        Branch branch2 = new Branch(2L, "Secondary Branch", 1L, products);
        Mockito.when(branchInPort.findAll()).thenReturn(Flux.just(branch, branch2));

        StepVerifier.create(branchController.getAllBranches())
                .expectNext(branch)
                .expectNext(branch2)
                .verifyComplete();
    }

    @Test
    void updateBranchName() {
        Branch updatedBranch = new Branch(1L, "Updated Branch", 1L, products);
        BranchResponse updatedResponse = new BranchResponse(
                updatedBranch.id(), updatedBranch.name(), updatedBranch.franchiseId()
        );

        Mockito.when(branchInPort.updateName(1L, "Updated Branch")).thenReturn(Mono.just(updatedBranch));
        Mockito.when(branchMapper.toResponse(updatedBranch)).thenReturn(updatedResponse);

        StepVerifier.create(branchController.updateBranchName(1L, "Updated Branch"))
                .expectNext(updatedResponse)
                .verifyComplete();
    }
}