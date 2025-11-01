package com.accenture.test_accenture.infraestructure.web.controllers;

import com.accenture.test_accenture.application.port.ProductBranch;
import com.accenture.test_accenture.application.port.in.FranchiseInPort;
import com.accenture.test_accenture.domain.Franchise;
import com.accenture.test_accenture.infraestructure.mappers.FranchiseMapper;
import com.accenture.test_accenture.infraestructure.web.dtos.FranchiseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseInPort franchiseInPort;
    private final FranchiseMapper franchiseMapper;

    public FranchiseController(FranchiseInPort franchiseInPort, FranchiseMapper franchiseMapper) {
        this.franchiseInPort = franchiseInPort;
        this.franchiseMapper = franchiseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FranchiseResponse> saveFranchise(@RequestBody Franchise franchise){
        return franchiseInPort.save(franchise)
                .map(franchiseMapper::toResponse);
    }

    @GetMapping
    public Flux<Franchise> getAllFranchises(){
        return franchiseInPort.findAll();
    }

    @PutMapping("/{id}")
    public Mono<FranchiseResponse> updateName(@PathVariable("id") Long franchiseId, @RequestParam String name){
        return franchiseInPort.updateName(franchiseId, name)
                .map(franchiseMapper::toResponse);
    }

    @GetMapping("/{id}/productos-max-stock")
    public Flux<ProductBranch> getProductWithMaxStockPerBranchForFranchise(@PathVariable Long id) {
        return franchiseInPort.findProductWithMaxStockPerBranchForFranchise(id);
    }

}
