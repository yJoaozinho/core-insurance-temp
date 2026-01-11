package com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence;

import com.assurant.insurance.core_insurance_platform.policy.infrastructure.persistence.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPolicyRepository extends JpaRepository<PolicyEntity, UUID> {
}
