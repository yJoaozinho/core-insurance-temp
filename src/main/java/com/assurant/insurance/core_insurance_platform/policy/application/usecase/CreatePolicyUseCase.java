package com.assurant.insurance.core_insurance_platform.policy.application.usecase;

import com.assurant.insurance.core_insurance_platform.policy.application.command.CreatePolicyCommand;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.*;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CreatePolicyUseCase {

    private final PolicyRepository policyRepository;

    public CreatePolicyUseCase(PolicyRepository policyRepository){
        this.policyRepository = policyRepository;
    }

    public PolicyId execute(CreatePolicyCommand command){

        PolicyId policyId = new PolicyId(UUID.fromString(command.policyId()));


        if(policyRepository.existsById(policyId)){
            throw new IllegalStateException("Policy already exists.");
        }

        PolicyHolderId holderId = new PolicyHolderId(UUID.fromString(command.policyHolderId()));

        List<Coverage> coverages = command.coverageCodes().stream().map(Coverage::new).toList();

        Premium premium = new Premium(BigDecimal.valueOf(command.premiumAmount()));

        Policy policy = new Policy(
                policyId,
                holderId,
                coverages,
                premium
        );

        policyRepository.save(policy);

        return policyId;

    }

}
