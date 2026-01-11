package com.assurant.insurance.core_insurance_platform.policy.application.command;
import java.util.List;

public record CreatePolicyCommand(String policyId,
                                  String policyHolderId,
                                  List<String> coverageCodes,
                                  double premiumAmount) {
}
