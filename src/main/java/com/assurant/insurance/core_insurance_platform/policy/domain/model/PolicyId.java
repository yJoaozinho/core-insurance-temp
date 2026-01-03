package com.assurant.insurance.core_insurance_platform.policy.domain.model;

import java.util.Objects;
import java.util.UUID;

public class PolicyId {

    private final UUID value;

    public PolicyId(UUID value){
        this.value = Objects.requireNonNull(value, "PolicyId cannot be null");
    }

    public static PolicyId newID(){
        return new PolicyId(UUID.randomUUID());
    }

    public UUID value(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof PolicyId)) return false;
        PolicyId policyId = (PolicyId) o;
        return value.equals(policyId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
