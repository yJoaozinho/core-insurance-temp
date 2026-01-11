package com.assurant.insurance.core_insurance_platform.policy.domain.model;
import java.util.Objects;
public class CancellationReason {

    private final String reason;

    public CancellationReason(String reason){
        this.reason = Objects.requireNonNull(reason, "Cancellation reason is required");
    }

    public String getReason(){
        return reason;
    }

}
