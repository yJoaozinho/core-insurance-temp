package com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.repository;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.*;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;
import com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.SpringDataPolicyRepository;
import com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaPolicyRepository implements PolicyRepository {

    private final SpringDataPolicyRepository springDataRepository;

    public JpaPolicyRepository(SpringDataPolicyRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public void save(Policy policy) {
        PolicyEntity entity = toEntity(policy);
        springDataRepository.save(entity);
    }

    @Override
    public Optional<Policy> findById(PolicyId id) {
        return springDataRepository.findById(id.value()).map(this::toDomain);
    }

    @Override
    public boolean existsById(PolicyId id) {
        return springDataRepository.existsById(id.value());
    }

    private PolicyEntity toEntity(Policy policy) {
        PolicyEntity entity = new PolicyEntity();
        entity.setId(policy.id().value());
        entity.setHolderId(policy.holderId().value());
        entity.setStatus(policy.status());
        entity.setPremiumAmount(policy.premium().amount());
        entity.setCreatedAt(policy.createdAt());
        entity.setActivatedAt(policy.activatedAt());
        entity.setCancelledAt(policy.cancelledAt());

        if (policy.cancellationReason() != null) {
            entity.setCancellationReason(policy.cancellationReason().getReason());
            entity.setCancellationDate(policy.cancellationDate());
        }

        for (Coverage cov : policy.coverages()) {
            CoverageEntity covEntity = new CoverageEntity();
            covEntity.setId(cov.GetCoverageId());
            covEntity.setDescription(cov.getDescription());
            entity.addCoverage(covEntity);
        }
        return entity;
    }

    private Policy toDomain(PolicyEntity entity) {
        List<Coverage> coverages = entity.getCoverages().stream()
                .map(c -> Coverage.rehydrate(c.getId(), c.getDescription()))
                .collect(Collectors.toList());

        return Policy.rehydrate(
                new PolicyId(entity.getId()),
                new PolicyHolderId(entity.getHolderId()),
                entity.getStatus(),
                coverages,
                new Premium(entity.getPremiumAmount()),
                entity.getCreatedAt(),
                entity.getActivatedAt(),
                entity.getCancelledAt(),
                entity.getCancellationReason() != null ? new CancellationReason(entity.getCancellationReason()) : null,
                entity.getCancellationDate()
        );
    }
}