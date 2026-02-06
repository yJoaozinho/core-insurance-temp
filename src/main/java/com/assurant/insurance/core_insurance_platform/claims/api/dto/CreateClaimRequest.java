package com.assurant.insurance.core_insurance_platform.claims.api.dto;

import java.math.BigDecimal;

public record CreateClaimRequest(
        String policyId,
        BigDecimal amount
) {
}