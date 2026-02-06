package com.assurant.insurance.core_insurance_platform.claims.application.command;

public record SubmitClaimCommand(String policyId,
                                 double claimAmount
) {
}
