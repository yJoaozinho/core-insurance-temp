package com.assurant.insurance.core_insurance_platform.policy.domain.event;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;

import java.time.LocalDateTime;

public class PolicyActivated implements DomainEvent{

    private final PolicyId policyId;
    private final LocalDateTime occurredAt;

    public PolicyActivated(PolicyId policyId) {
        this.policyId = policyId;
        this.occurredAt = LocalDateTime.now();
    }

    public PolicyId policyId() {
        return policyId;
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }

}
