package com.assurant.insurance.core_insurance_platform.claims.domain.event;

import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;

import java.time.LocalDateTime;
import java.util.Objects;

public class ClaimRejected implements DomainEvent {

    private final ClaimId claimId;
    private final String reason;
    private final LocalDateTime occurredAt;

    public ClaimRejected(ClaimId claimId, String reason) {
        this.claimId = Objects.requireNonNull(claimId);
        this.reason = Objects.requireNonNull(reason, "Rejection reason is mandatory in the event");
        this.occurredAt = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }

    public ClaimId claimId() {
        return claimId;
    }

    public String reason() {
        return reason;
    }
}