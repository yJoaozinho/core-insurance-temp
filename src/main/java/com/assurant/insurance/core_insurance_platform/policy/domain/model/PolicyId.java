package com.assurant.insurance.core_insurance_platform.policy.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class PolicyId { // final: ninguém herda dela
    private final UUID value;

    public PolicyId(UUID value) {
        // O ID já nasce validado. Nunca existirá um PolicyId nulo no seu sistema.
        this.value = Objects.requireNonNull(value, "PolicyId cannot be null");
    }

    // O metodo "janela" que conversamos para o JPA conseguir ler
    public UUID value() {
        return value;
    }

    // OBRIGATÓRIO em Value Objects: Comparar por valor, não por endereço de memória
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyId policyId = (PolicyId) o;
        return Objects.equals(value, policyId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}