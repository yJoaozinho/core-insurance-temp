package com.assurant.insurance.core_insurance_platform.policy.api.dto;

public record CancelPolicyRequest(String cancellationReason,
                                  String cancellationDate) {
}
