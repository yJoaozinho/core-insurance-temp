package com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "coverages")
public class CoverageEntity {
    @Id
    private UUID id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private PolicyEntity policy;

    public CoverageEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public PolicyEntity getPolicy() { return policy; }
    public void setPolicy(PolicyEntity policy) { this.policy = policy; }
}