package com.assurant.insurance.core_insurance_platform.policy.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class PolicyId { // final: ninguém herda dela
    private final UUID value;

    public PolicyId(UUID value) {

        this.value = Objects.requireNonNull(value, "PolicyId cannot be null");
    }


    public UUID value() {
        return value;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyId policyId = (PolicyId) o;
        return Objects.equals(value, policyId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}