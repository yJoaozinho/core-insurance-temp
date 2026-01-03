package com.assurant.insurance.core_insurance_platform.policy.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Premium {

    private final BigDecimal amount;

    public Premium (BigDecimal amount) {
        Objects.requireNonNull(amount, "Premium amount cannot be null.");

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Premium must be greater than zero.");
        }
        this.amount = amount;
    }

    public BigDecimal amount(){
        return amount;
    }

}
