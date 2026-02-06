package com.assurant.insurance.core_insurance_platform.claims.domain.event;

import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClaimSubmitted implements DomainEvent{

    private final ClaimId claimId;
    private final PolicyId policyId;
    private final BigDecimal amount;
    private final LocalDateTime occurredAt;

    public ClaimSubmitted(
            ClaimId claimId,
            PolicyId policyId,
            BigDecimal amount
    ) {
        this.claimId = claimId;
        this.policyId = policyId;
        this.amount = amount;
        this.occurredAt = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }

    public ClaimId claimId() {
        return claimId;
    }

    public PolicyId policyId() {
        return policyId;
    }

    public BigDecimal amount() {
        return amount;
    }

}
