package com.assurant.insurance.core_insurance_platform.claims.infrastructure.persistence.entity;

import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "claims")
public class ClaimEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID policyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    private String rejectionReason;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime approvedAt;
    private LocalDateTime rejectedAt;

    public ClaimEntity() {
        // JPA
    }

    // Getters e setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getPolicyId() { return policyId; }
    public void setPolicyId(UUID policyId) { this.policyId = policyId; }

    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }

    public LocalDateTime getRejectedAt() { return rejectedAt; }
    public void setRejectedAt(LocalDateTime rejectedAt) { this.rejectedAt = rejectedAt; }
}
