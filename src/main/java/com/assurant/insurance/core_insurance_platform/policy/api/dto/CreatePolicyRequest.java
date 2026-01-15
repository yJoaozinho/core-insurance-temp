package com.assurant.insurance.core_insurance_platform.policy.api.dto;

import java.util.List;

public record CreatePolicyRequest(String policyHolderId,
                                  List<String> coverageCodes,
                                  double premiumAmount) {
}
