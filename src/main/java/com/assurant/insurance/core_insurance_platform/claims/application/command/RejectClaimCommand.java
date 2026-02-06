package com.assurant.insurance.core_insurance_platform.claims.application.command;

public record RejectClaimCommand(String claimId,
                                 String reason) {
}
