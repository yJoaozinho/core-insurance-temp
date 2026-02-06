package com.assurant.insurance.core_insurance_platform.claims.domain.event;

import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;

import java.time.LocalDateTime;

public class ClaimApproved implements DomainEvent{

    private final ClaimId claimId;
    private final LocalDateTime occurredAt;

    public ClaimApproved(ClaimId claimId) {
        this.claimId = claimId;
        this.occurredAt = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }

    public ClaimId claimId() {
        return claimId;
    }

}
