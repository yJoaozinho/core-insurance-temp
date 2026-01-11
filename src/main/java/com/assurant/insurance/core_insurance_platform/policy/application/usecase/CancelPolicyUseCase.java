package com.assurant.insurance.core_insurance_platform.policy.application.usecase;

import com.assurant.insurance.core_insurance_platform.policy.application.command.CancelPolicyCommand;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.CancellationReason;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.Policy;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;

import java.time.LocalDate;
import java.util.UUID;

public class CancelPolicyUseCase {

    private final PolicyRepository policyRepository;

    public CancelPolicyUseCase(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public void execute(CancelPolicyCommand command) {

        PolicyId policyId =
                new PolicyId(UUID.fromString(command.policyId()));

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new IllegalStateException("Policy not found"));

        CancellationReason reason = new CancellationReason(command.cancellationReason());

        LocalDate cancellationDate =
                LocalDate.parse(command.cancellationDate());

        policy.cancel(reason, cancellationDate);

        policyRepository.save(policy);
    }
}
