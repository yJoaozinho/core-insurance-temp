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

        // 1. Buscar policy
        PolicyId policyId = new PolicyId(UUID.fromString(command.policyId()));
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new IllegalStateException("Policy not found"));

        // 2. Criar snapshot da policy
        PolicySnapshot snapshot = PolicySnapshot.from(policy);

        // 3. Criar claim via domínio
        ClaimId claimId = ClaimId.generate();
        Claim claim = Claim.submit(
                claimId,
                snapshot,
                new ClaimAmount(command.amount())
        );

        // 4. Persistir
        claimRepository.save(claim);

        // 5. Retornar id
        return claimId;
    }

}
