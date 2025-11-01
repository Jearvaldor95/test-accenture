package com.accenture.test_accenture.infraestructure.web.controllers;

import com.accenture.test_accenture.application.port.in.BranchInPort;
import com.accenture.test_accenture.domain.Branch;
import com.accenture.test_accenture.infraestructure.mappers.BranchMapper;
import com.accenture.test_accenture.infraestructure.web.dtos.BranchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchInPort branchInPort;
    private final BranchMapper branchMapper;

    public BranchController(BranchInPort branchInPort, BranchMapper branchMapper) {
        this.branchInPort = branchInPort;
        this.branchMapper = branchMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BranchResponse> saveBranch(@RequestBody Branch branch) {
        return branchInPort.save(branch)
                .map(branchMapper::toResponse);
    }

    @GetMapping
    public Flux<Branch> getAllBranches() {
        return branchInPort.findAll();
    }

    @PutMapping("/{id}")
    public Mono<BranchResponse> updateBranchName(@PathVariable("id") Long branchId, @RequestParam String name) {
        return branchInPort.updateName(branchId, name)
                .map(branchMapper::toResponse);
    }
}
