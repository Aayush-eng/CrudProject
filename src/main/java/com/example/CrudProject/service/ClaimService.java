package com.example.CrudProject.service;

import com.example.CrudProject.entity.Claim;
import com.example.CrudProject.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public Optional<Claim> updateClaim(Long id, Claim claim) {
        return claimRepository.findById(id)
                .map(existingClaim -> {
                    claim.setClaimId(id); // Ensure the ID is set
                    return claimRepository.save(claim);
                });
    }

    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }
}