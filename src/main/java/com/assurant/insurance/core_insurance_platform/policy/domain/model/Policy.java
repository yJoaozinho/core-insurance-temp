package com.assurant.insurance.core_insurance_platform.policy.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Policy {

    private final PolicyId id;

    private PolicyStatus status;
    private final List<Coverage> coverages;
    private final Premium premium;

    // ---- Audit fields (business-relevant) ----
    private final LocalDateTime createdAt;
    private LocalDateTime activatedAt;
    private LocalDateTime cancelledAt;

    private CancellationReason cancellationReason;
    private LocalDate cancellationDate;

    public Policy(PolicyId id, List<Coverage> coverages, Premium premium) {
        this.id = Objects.requireNonNull(id, "PolicyId is required");

        if (coverages == null || coverages.isEmpty()) {
            throw new IllegalArgumentException("Policy must have at least one coverage");
        }

        this.coverages = new ArrayList<>(coverages);
        this.premium = Objects.requireNonNull(premium, "Premium is required");

        this.status = PolicyStatus.DRAFT;
        this.createdAt = LocalDateTime.now();
    }

    // ---- Behavior ----

    public void activate() {
        if (status != PolicyStatus.DRAFT) {
            throw new IllegalStateException("Only DRAFT policies can be activated");
        }

        this.status = PolicyStatus.ACTIVE;
        this.activatedAt = LocalDateTime.now();
    }

    public void cancel(CancellationReason reason, LocalDate cancellationDate) {
        if (status != PolicyStatus.ACTIVE) {
            throw new IllegalStateException("Only ACTIVE policies can be cancelled");
        }

        this.cancellationReason = Objects.requireNonNull(reason, "Cancellation reason is required");
        this.cancellationDate = Objects.requireNonNull(cancellationDate, "Cancellation date is required");

        this.status = PolicyStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
    }

    // ---- Query methods (safe exposure) ----

    public PolicyId id() {
        return id;
    }

    public PolicyStatus status() {
        return status;
    }

    public List<Coverage> coverages() {
        return Collections.unmodifiableList(coverages);
    }

    public Premium premium() {
        return premium;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime activatedAt() {
        return activatedAt;
    }

    public LocalDateTime cancelledAt() {
        return cancelledAt;
    }

    public CancellationReason cancellationReason() {
        return cancellationReason;
    }

    public LocalDate cancellationDate() {
        return cancellationDate;
    }
}
