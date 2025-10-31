package com.accenture.test_accenture.infraestructure.web.controllers;

import com.accenture.test_accenture.application.port.in.FranchiseInPort;
import com.accenture.test_accenture.domain.Franchise;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseInPort franchiseInPort;

    public FranchiseController(FranchiseInPort franchiseInPort) {
        this.franchiseInPort = franchiseInPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franchise> saveFranchise(@RequestBody Franchise franchise){
        return franchiseInPort.save(franchise);
    }

    @GetMapping
    public Flux<Franchise> getAllFranchises(){
        return franchiseInPort.findAll();
    }

    @PutMapping("/{id}")
    public Mono<Franchise> updateName(@PathVariable("id") Long franchiseId, @RequestParam String name){
        return franchiseInPort.updateName(franchiseId, name);
    }
}
