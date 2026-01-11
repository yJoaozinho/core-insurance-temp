package com.assurant.insurance.core_insurance_platform.policy.application.command;

public record CancelPolicyCommand(
        String policyId,
        String cancellationReason,
        String cancellationDate
) {
}
