package com.assurant.insurance.core_insurance_platform.claims.domain.model;

import com.assurant.insurance.core_insurance_platform.claims.domain.event.ClaimApproved;
import com.assurant.insurance.core_insurance_platform.claims.domain.event.ClaimRejected;
import com.assurant.insurance.core_insurance_platform.claims.domain.event.ClaimSubmitted;
import com.assurant.insurance.core_insurance_platform.claims.domain.event.DomainEvent;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Claim {

    private final ClaimId id;
    private final PolicyId policyId;
    private ClaimStatus status;
    private final ClaimAmount amount;

    private String rejectionReason;

    private final LocalDateTime createdAt;
    private LocalDateTime approvedAt;
    private LocalDateTime rejectedAt;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    // ---------- Constructors ----------

    // Criação de NOVO claim
    private Claim(ClaimId id, PolicyId policyId, ClaimAmount amount) {
        this.id = Objects.requireNonNull(id);
        this.policyId = Objects.requireNonNull(policyId);
        this.amount = Objects.requireNonNull(amount);

        this.status = ClaimStatus.SUBMITTED;
        this.createdAt = LocalDateTime.now();

        this.domainEvents.add(
                new ClaimSubmitted(this.id, this.policyId, this.amount.value())
        );
    }

    // Rehidratação (vindo do banco)
    private Claim(
            ClaimId id,
            PolicyId policyId,
            ClaimStatus status,
            ClaimAmount amount,
            String rejectionReason,
            LocalDateTime createdAt,
            LocalDateTime approvedAt,
            LocalDateTime rejectedAt
    ) {
        this.id = id;
        this.policyId = policyId;
        this.status = status;
        this.amount = amount;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.approvedAt = approvedAt;
        this.rejectedAt = rejectedAt;
    }

    // ---------- Factories ----------

    /**
     * Submissão de um novo claim.
     * Regras automáticas são aplicadas aqui.
     */
    public static Claim submit(
            ClaimId id,
            PolicySnapshot policySnapshot,
            ClaimAmount amount
    ) {
        Objects.requireNonNull(policySnapshot);

        Claim claim = new Claim(id, policySnapshot.policyId(), amount);

        // Regra 1: policy precisa estar ativa
        if (!policySnapshot.isActive()) {
            claim.reject("Policy is not active");
            return claim;
        }

        // Regra 2: valor do sinistro não pode exceder o premium
        if (policySnapshot.isExceededBy(amount)) {
            claim.reject("Claim amount exceeds policy premium");
            return claim;
        }

        return claim;
    }

    public static Claim rehydrate(
            ClaimId id,
            PolicyId policyId,
            ClaimStatus status,
            ClaimAmount amount,
            String rejectionReason,
            LocalDateTime createdAt,
            LocalDateTime approvedAt,
            LocalDateTime rejectedAt
    ) {
        return new Claim(
                id,
                policyId,
                status,
                amount,
                rejectionReason,
                createdAt,
                approvedAt,
                rejectedAt
        );
    }

    // ---------- Business behavior ----------

    public void approve() {
        if (status != ClaimStatus.SUBMITTED) {
            throw new IllegalStateException("Only submitted claims can be approved");
        }

        this.status = ClaimStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
        this.rejectionReason = null;

        domainEvents.add(new ClaimApproved(this.id));
    }

    public void reject(String reason) {
        if (status != ClaimStatus.SUBMITTED) {
            throw new IllegalStateException("Only submitted claims can be rejected");
        }

        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("A rejection reason must be provided");
        }

        this.status = ClaimStatus.REJECTED;
        this.rejectionReason = reason;
        this.rejectedAt = LocalDateTime.now();

        domainEvents.add(new ClaimRejected(this.id, reason));
    }

    // ---------- Getters ----------

    public ClaimId id() { return id; }
    public PolicyId policyId() { return policyId; }
    public ClaimStatus status() { return status; }
    public ClaimAmount amount() { return amount; }
    public String rejectionReason() { return rejectionReason; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime approvedAt() { return approvedAt; }
    public LocalDateTime rejectedAt() { return rejectedAt; }

    public List<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}
