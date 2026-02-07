package com.assurant.insurance.core_insurance_platform.claims.application.usecase;

import com.assurant.insurance.core_insurance_platform.claims.application.command.CreateClaimCommand;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.Claim;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimAmount;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.PolicySnapshot;
import com.assurant.insurance.core_insurance_platform.claims.domain.repository.ClaimRepository;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.Policy;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;

import java.util.UUID;

public class CreateClaimUseCase {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public CreateClaimUseCase(
            ClaimRepository claimRepository,
            PolicyRepository policyRepository
    ) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    public ClaimId execute(CreateClaimCommand command) {

        Policy policy = policyRepository.findById(
                new PolicyId(UUID.fromString(command.policyId()))
        ).orElseThrow(() ->
                new IllegalStateException("Policy not found")
        );

        PolicySnapshot snapshot = PolicySnapshot.from(policy);

        Claim claim = Claim.submit(
                new ClaimId(UUID.randomUUID()),
                snapshot,
                new ClaimAmount(command.amount())
        );

        claimRepository.save(claim);

        return claim.id();
    }

}
