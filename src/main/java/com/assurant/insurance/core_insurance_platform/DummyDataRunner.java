package com.assurant.insurance.core_insurance_platform;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.*;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class DummyDataRunner implements CommandLineRunner {

    private final PolicyRepository policyRepository;

    public DummyDataRunner(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Criando os dados de domínio
        PolicyId policyId = new PolicyId(UUID.randomUUID());
        PolicyHolderId holderId = new PolicyHolderId(UUID.randomUUID());

        List<Coverage> coverages = List.of(
                new Coverage("Incêndio"),
                new Coverage("Roubo")
        );

        Premium premium = new Premium(new BigDecimal("1500.00"));

        // 2. Criando a Entidade de Domínio
        Policy policy = new Policy(policyId, holderId, coverages, premium);

        // 3. Salvando através do seu JpaPolicyRepository
        policyRepository.save(policy);

        System.out.println(">>> Apólice salva com sucesso no banco H2!");
    }
}