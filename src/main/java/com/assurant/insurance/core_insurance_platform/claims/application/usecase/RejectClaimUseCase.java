package com.assurant.insurance.core_insurance_platform.claims.application.usecase;

import com.assurant.insurance.core_insurance_platform.claims.application.command.RejectClaimCommand;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.Claim;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;
import com.assurant.insurance.core_insurance_platform.claims.domain.repository.ClaimRepository;

import java.util.UUID;

public class RejectClaimUseCase {

    private final ClaimRepository claimRepository;

    public RejectClaimUseCase(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public void execute(RejectClaimCommand command) {

        ClaimId claimId = new ClaimId(UUID.fromString(command.claimId()));

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new IllegalStateException("Claim not found"));

        claim.reject(command.reason());

        claimRepository.save(claim);
    }


}
