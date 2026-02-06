package com.assurant.insurance.core_insurance_platform.claims.domain.model;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.Policy;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyStatus;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.Premium;

import java.math.BigDecimal;

public class PolicySnapshot {
    private final PolicyId policyId;
    private final PolicyStatus policyStatus;
    private final Premium premium;

    public PolicySnapshot(PolicyId policyId, PolicyStatus policyStatus, Premium premium) {
        this.policyId = policyId;
        this.policyStatus = policyStatus;
        this.premium = premium;
    }

    // Metodo necessário para o erro 1
    public PolicyId policyId() {
        return policyId;
    }

    // Metodo necessário para o erro 2 (retornando o valor numérico)
    public BigDecimal premiumAmountValue() {
        return premium.value();
    }

    // Padrão Factory: Converte o Agregado Policy em um Snapshot estável
    public static PolicySnapshot from(Policy policy) {
        return new PolicySnapshot(
                policy.id(),
                policy.status(),
                policy.premium()
        );
    }

    public boolean isActive() {
        return policyStatus == PolicyStatus.ACTIVE;
    }

    // Melhora o encapsulamento: a decisão de excesso de valor fica aqui
    public boolean isExceededBy(ClaimAmount claimAmount) {
        return claimAmount.value().compareTo(premium.value()) > 0;
    }
}
