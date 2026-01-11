package com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.repository;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.Policy;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;
import com.assurant.insurance.core_insurance_platform.policy.domain.repository.PolicyRepository;
import com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.SpringDataPolicyRepository;
import com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.entity.PolicyEntity;

import java.util.Optional;

public class JpaPolicyRepository implements PolicyRepository {

    private final SpringDataPolicyRepository repository;

    public JpaPolicyRepository(SpringDataPolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Policy policy) {
        PolicyEntity entity = toEntity(policy);
        repository.save(entity);
    }

    @Override
    public Optional<Policy> findById(PolicyId id) {
        return repository.findById(id.value())
                .map(this::toDomain);
    }

    @Override
    public boolean existsById(PolicyId id) {
        return false;
    }

    // ---------------- mapping ----------------

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

        return entity;
    }

    private Policy toDomain(PolicyEntity entity) {
        return Policy.rehydrate(
                new PolicyId(entity.getId()),
                new PolicyHolderId(entity.getHolderId()),
                entity.getStatus(),
                new Premium(entity.getPremiumAmount()),
                entity.getCreatedAt(),
                entity.getActivatedAt(),
                entity.getCancelledAt(),
                entity.getCancellationReason() != null
                        ? new CancellationReason(entity.getCancellationReason())
                        : null,
                entity.getCancellationDate()
        );
    }
}
