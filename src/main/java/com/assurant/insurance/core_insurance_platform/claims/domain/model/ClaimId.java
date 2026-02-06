package com.assurant.insurance.core_insurance_platform.claims.domain.model;

import java.util.Objects;
import java.util.UUID;

public record ClaimId(UUID value) {
    public ClaimId {
        Objects.requireNonNull(value, "ClaimId value cannot be null");
    }

    // O Use Case chamará este método
    public static ClaimId generate() {
        return new ClaimId(UUID.randomUUID());
    }

    // Útil para converter a String que vem da API
    public static ClaimId fromString(String id) {
        return new ClaimId(UUID.fromString(id));
    }
}
