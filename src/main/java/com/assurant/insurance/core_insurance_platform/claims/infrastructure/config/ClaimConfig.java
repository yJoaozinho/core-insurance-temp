package com.assurant.insurance.core_insurance_platform.claims.infrastructure.config;

import com.assurant.insurance.core_insurance_platform.claims.application.usecase.ApproveClaimUseCase;
import com.assurant.insurance.core_insurance_platform.claims.application.usecase.CreateClaimUseCase;
import com.assurant.insurance.core_insurance_platform.claims.application.usecase.RejectClaimUseCase;
import com.assurant.insurance.core_insurance_platform.claims.domain.repository.ClaimRepository;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClaimConfig {

    @Bean
    public CreateClaimUseCase createClaimUseCase(
            ClaimRepository claimRepository,
            PolicyRepository policyRepository
    ) {
        return new CreateClaimUseCase(claimRepository, policyRepository);
    }

    @Bean
    public ApproveClaimUseCase approveClaimUseCase(ClaimRepository repository) {
        return new ApproveClaimUseCase(repository);
    }

    @Bean
    public RejectClaimUseCase rejectClaimUseCase(ClaimRepository repository) {
        return new RejectClaimUseCase(repository);
    }
}