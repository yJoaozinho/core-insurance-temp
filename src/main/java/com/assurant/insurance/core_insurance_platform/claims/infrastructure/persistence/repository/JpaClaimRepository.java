package com.assurant.insurance.core_insurance_platform.claims.infrastructure.persistence.repository;

import com.assurant.insurance.core_insurance_platform.claims.domain.model.*;
import com.assurant.insurance.core_insurance_platform.claims.domain.repository.ClaimRepository;
import com.assurant.insurance.core_insurance_platform.claims.infrastructure.persistence.entity.ClaimEntity;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaClaimRepository implements ClaimRepository {

    private final SpringDataClaimRepository springDataRepository;

    public JpaClaimRepository(SpringDataClaimRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public void save(Claim claim) {
        ClaimEntity entity = toEntity(claim);
        springDataRepository.save(entity);
    }

    @Override
    public Optional<Claim> findById(ClaimId id) {
        return springDataRepository.findById(id.value())
                .map(this::toDomain);
    }

    @Override
    public boolean existsById(ClaimId claimId) {
        return false;
    }

    private ClaimEntity toEntity(Claim claim) {
        ClaimEntity entity = new ClaimEntity();
        entity.setId(claim.id().value());
        entity.setPolicyId(claim.policyId().value());
        entity.setStatus(claim.status());
        entity.setAmount(claim.amount().value());
        entity.setCreatedAt(claim.createdAt());
        entity.setApprovedAt(claim.approvedAt());
        entity.setRejectedAt(claim.rejectedAt());
        entity.setRejectionReason(claim.rejectionReason());
        return entity;
    }

    private Claim toDomain(ClaimEntity entity) {
        return Claim.rehydrate(
                new ClaimId(entity.getId()),
                new PolicyId(entity.getPolicyId()),
                entity.getStatus(),
                new ClaimAmount(entity.getAmount()),
                entity.getRejectionReason(),
                entity.getCreatedAt(),
                entity.getApprovedAt(),
                entity.getRejectedAt()
        );
    }
}
