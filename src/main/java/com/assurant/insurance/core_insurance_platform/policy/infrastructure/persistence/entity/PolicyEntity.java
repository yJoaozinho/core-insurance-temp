package com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.entity;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "policies")
public class PolicyEntity {
    @Id
    private UUID id;
    private UUID holderId;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    private BigDecimal premiumAmount;
    private LocalDateTime createdAt;
    private LocalDateTime activatedAt;
    private LocalDateTime cancelledAt;
    private String cancellationReason;
    private LocalDate cancellationDate;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CoverageEntity> coverages = new ArrayList<>();

    public PolicyEntity() {}

    public void addCoverage(CoverageEntity coverage) {
        coverages.add(coverage);
        coverage.setPolicy(this);
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getHolderId() { return holderId; }
    public void setHolderId(UUID holderId) { this.holderId = holderId; }
    public PolicyStatus getStatus() { return status; }
    public void setStatus(PolicyStatus status) { this.status = status; }
    public BigDecimal getPremiumAmount() { return premiumAmount; }
    public void setPremiumAmount(BigDecimal premiumAmount) { this.premiumAmount = premiumAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getActivatedAt() { return activatedAt; }
    public void setActivatedAt(LocalDateTime activatedAt) { this.activatedAt = activatedAt; }
    public LocalDateTime getCancelledAt() { return cancelledAt; }
    public void setCancelledAt(LocalDateTime cancelledAt) { this.cancelledAt = cancelledAt; }
    public String getCancellationReason() { return cancellationReason; }
    public void setCancellationReason(String cancellationReason) { this.cancellationReason = cancellationReason; }
    public LocalDate getCancellationDate() { return cancellationDate; }
    public void setCancellationDate(LocalDate cancellationDate) { this.cancellationDate = cancellationDate; }
    public List<CoverageEntity> getCoverages() { return coverages; }
    public void setCoverages(List<CoverageEntity> coverages) { this.coverages = coverages; }
}