package com.assurant.insurance.core_insurance_platform.policy.domain.model;
import java.util.Objects;
import java.util.UUID;
public class PolicyHolderId {

    private final UUID value;

    public PolicyHolderId(UUID value){
        this.value = Objects.requireNonNull(value, "PolicyHolderId cannot be null");
    }

    public UUID getPolicyHolderId() {
        return value;
    }

}
