package com.assurant.insurance.core_insurance_platform.policy.domain.event;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.CancellationReason;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PolicyCancelled implements DomainEvent{

    private final PolicyId policyId;
    private final CancellationReason reason;
    private final LocalDate cancellationDate;
    private final LocalDateTime occurredAt;

    public PolicyCancelled(
            PolicyId policyId,
            CancellationReason reason,
            LocalDate cancellationDate
    ) {
        this.policyId = policyId;
        this.reason = reason;
        this.cancellationDate = cancellationDate;
        this.occurredAt = LocalDateTime.now();
    }

    public PolicyId policyId() {
        return policyId;
    }

    public CancellationReason reason() {
        return reason;
    }

    public LocalDate cancellationDate() {
        return cancellationDate;
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }
}
