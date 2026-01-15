package com.assurant.insurance.core_insurance_platform.policy.infrastructure.config;

import com.assurant.insurance.core_insurance_platform.policy.application.usecase.ActivatePolicyUseCase;
import com.assurant.insurance.core_insurance_platform.policy.application.usecase.CancelPolicyUseCase;
import com.assurant.insurance.core_insurance_platform.policy.application.usecase.CreatePolicyUseCase;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PolicyConfig {
    @Bean
    public CreatePolicyUseCase createPolicyUseCase(PolicyRepository repository) {
        return new CreatePolicyUseCase(repository);
    }

    @Bean
    public ActivatePolicyUseCase activatePolicyUseCase(PolicyRepository repository) {
        return new ActivatePolicyUseCase(repository);
    }

    @Bean
    public CancelPolicyUseCase cancelPolicyUseCase(PolicyRepository repository) {
        return new CancelPolicyUseCase(repository);
    }
}
