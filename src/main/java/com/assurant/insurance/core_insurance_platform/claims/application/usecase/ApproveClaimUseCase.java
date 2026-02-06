package com.assurant.insurance.core_insurance_platform.claims.application.usecase;

import com.assurant.insurance.core_insurance_platform.claims.application.command.ApproveClaimCommand;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.Claim;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;
import com.assurant.insurance.core_insurance_platform.claims.domain.repository.ClaimRepository;

import java.util.UUID;

public class ApproveClaimUseCase {

    private final ClaimRepository claimRepository;

    public ApproveClaimUseCase(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public void execute(ApproveClaimCommand command) {

        ClaimId claimId = new ClaimId(UUID.fromString(command.claimId()));

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new IllegalStateException("Claim not found"));

        claim.approve();

        claimRepository.save(claim);
    }

}
