package com.accenture.test_accenture.infraestructure.web.controllers;

import com.accenture.test_accenture.application.port.in.BranchInPort;
import com.accenture.test_accenture.domain.Branch;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchInPort branchInPort;

    public BranchController(BranchInPort branchInPort) {
        this.branchInPort = branchInPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Branch> saveBranch(@RequestBody Branch branch) {
        return branchInPort.save(branch);
    }

    @GetMapping
    public Flux<Branch> getAllBranches() {
        return branchInPort.findAll();
    }

    @PutMapping("/{id}")
    public Mono<Branch> updateBranchName(@PathVariable("id") Long branchId, @RequestParam String name) {
        return branchInPort.updateName(branchId, name);
    }
}
