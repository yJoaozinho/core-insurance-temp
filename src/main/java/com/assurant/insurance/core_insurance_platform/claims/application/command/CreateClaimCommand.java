package com.assurant.insurance.core_insurance_platform.claims.application.command;

import java.math.BigDecimal;

public record CreateClaimCommand(String policyId,
                                 BigDecimal amount) {
}
