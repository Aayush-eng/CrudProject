package com.example.CrudProject.controller;

import com.example.CrudProject.entity.Claim;
import com.example.CrudProject.service.ClaimService; // Import ClaimService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService; // Inject ClaimService

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Claim>> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claimService.createClaim(claim));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Claim>> updateClaim(@PathVariable Long id, @RequestBody Claim claim) {
        return ResponseEntity.ok(claimService.updateClaim(id, claim));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }
}