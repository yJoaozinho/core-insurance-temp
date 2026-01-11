package com.assurant.insurance.core_insurance_platform.policy.domain.event;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyHolderId;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;

import java.time.LocalDateTime;

public class PolicyCreated implements DomainEvent{

    private final PolicyId policyId;
    private final PolicyHolderId policyHolderId;
    private final LocalDateTime occurredAt;

    public PolicyCreated(PolicyId policyId, PolicyHolderId policyHolderId) {
        this.policyId = policyId;
        this.policyHolderId = policyHolderId;
        this.occurredAt = LocalDateTime.now();
    }

    public PolicyId policyId() {
        return policyId;
    }

    public PolicyHolderId policyHolderId() {
        return policyHolderId;
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }
}
