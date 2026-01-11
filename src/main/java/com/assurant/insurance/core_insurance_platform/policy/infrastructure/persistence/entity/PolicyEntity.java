package com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.entity;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "policies")
public class PolicyEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "holder_id", nullable = false)
    private UUID holderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PolicyStatus status;

    @Column(name = "premium_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal premiumAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "activated_at")
    private LocalDateTime activatedAt;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Column(name = "cancellation_date")
    private LocalDate cancellationDate;

    public PolicyEntity() {
        // JPA
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setHolderId(UUID holderId) {
        this.holderId = holderId;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setActivatedAt(LocalDateTime activatedAt) {
        this.activatedAt = activatedAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getHolderId() {
        return holderId;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getActivatedAt() {
        return activatedAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }
}
