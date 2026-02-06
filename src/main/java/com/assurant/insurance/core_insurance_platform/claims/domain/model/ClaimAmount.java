package com.assurant.insurance.core_insurance_platform.claims.domain.model;

import java.math.BigDecimal;

public class ClaimAmount {

    private final BigDecimal value;

    public ClaimAmount(BigDecimal value) {
        if (value == null || value.signum() <= 0) {
            throw new IllegalArgumentException("Claim amount must be positive");
        }
        this.value = value;
    }

    public BigDecimal value() {
        return value;
    }

}
