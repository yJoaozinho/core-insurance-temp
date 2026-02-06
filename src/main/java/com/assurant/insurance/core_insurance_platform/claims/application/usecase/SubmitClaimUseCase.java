package com.assurant.insurance.core_insurance_platform.claims.application.usecase;

import com.assurant.insurance.core_insurance_platform.claims.application.command.SubmitClaimCommand;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.Claim;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimAmount;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;
import com.assurant.insurance.core_insurance_platform.claims.domain.repository.ClaimRepository;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;

import java.math.BigDecimal;
import java.util.UUID;

public class SubmitClaimUseCase {

    private final ClaimRepository claimRepository;

    public SubmitClaimUseCase(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public ClaimId execute(SubmitClaimCommand command) {

        ClaimId claimId = new ClaimId(UUID.randomUUID());
        PolicyId policyId = new PolicyId(UUID.fromString(command.policyId()));
        ClaimAmount amount = new ClaimAmount(BigDecimal.valueOf(command.claimAmount()));

        Claim claim = Claim.submit(claimId, policyId, amount);

        claimRepository.save(claim);

        return claimId;
    }

}
