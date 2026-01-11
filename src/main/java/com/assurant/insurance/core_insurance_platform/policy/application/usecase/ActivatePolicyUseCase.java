package com.assurant.insurance.core_insurance_platform.policy.application.usecase;

import com.assurant.insurance.core_insurance_platform.policy.application.command.ActivatePolicyCommand;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.Policy;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;

import java.util.UUID;

public class ActivatePolicyUseCase {

    private final PolicyRepository policyRepository;

    public ActivatePolicyUseCase(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public void execute(ActivatePolicyCommand command) {

        PolicyId policyId =
                new PolicyId(UUID.fromString(command.policyId()));

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new IllegalStateException("Policy not found"));

        policy.activate();

        policyRepository.save(policy);
    }
}
